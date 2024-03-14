package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import CoinforgedPackage.util.CardStats;


public abstract class AbstractMultiPreviewCard extends BaseCard{
    public float rotationTimer;
    public int previewIndex;
    public ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();
    public ArrayList<CardTags> tagsToPreview;
    private static final float TIMER_DURATION = 1.25F;

    public abstract ArrayList<CardTags> getTags();

    public AbstractMultiPreviewCard(String id, CardStats info) {
        super(id, info);
        this.tagsToPreview = getTags();
        this.cardsToPreview = CardLibrary.cards.get("Madness"); // idk why but it doesn't work without this line
    }

    public ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> myList = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            for (CardTags tag : tagsToPreview) {
                if (q.hasTag(tag) && q.damage <= 6) { // damage <= 6 b/c red dice go up to 10
                    AbstractCard r = q.makeCopy();
                    myList.add(r);
                    break;
                }
            }
        }
        return myList;
    }

    @Override
    public void renderCardPreviewInSingleView(SpriteBatch sb) {
        if (this.dupeListForPrev.isEmpty()) {
            this.dupeListForPrev.addAll(getList());
        }
        updatePreview();
        super.renderCardPreviewInSingleView(sb);
    }

    @Override
    public void update() {
        super.update();
        if (this.dupeListForPrev.isEmpty()) {
            this.dupeListForPrev.addAll(getList());
        }
        if (this.hb.hovered) {
            updatePreview();
        }
    }

    public void updatePreview() {
        if (this.rotationTimer <= 0.0F) {
            this.rotationTimer = TIMER_DURATION;
            if (this.dupeListForPrev.isEmpty()) {
                this.cardsToPreview = CardLibrary.cards.get("Madness");
            } else {
                this.cardsToPreview = this.dupeListForPrev.get(this.previewIndex);
            }
            this.previewIndex = (this.previewIndex + 1) % this.dupeListForPrev.size();
        } else {
            this.rotationTimer -= Gdx.graphics.getDeltaTime();
        }
    }
}
