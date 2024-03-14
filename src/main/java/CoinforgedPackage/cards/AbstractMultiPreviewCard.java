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

    public abstract ArrayList<CardTags> getTags();

    public AbstractMultiPreviewCard(String id, CardStats info) {
        super(id, info);
        this.tagsToPreview = getTags();
    }

    public ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> myList = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            for (CardTags tag : tagsToPreview) {
                if (q.hasTag(tag) && q.damage <= 6) {
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
        if (this.rotationTimer <= 0.0F) {
            this.rotationTimer = 1.5F;
            if (this.dupeListForPrev.size() == 0) {
                this.cardsToPreview = (AbstractCard) CardLibrary.cards.get("Madness");
            } else {
                this.cardsToPreview = this.dupeListForPrev.get(this.previewIndex);
            }
            if (this.previewIndex == this.dupeListForPrev.size() - 1) {
                this.previewIndex = 0;
            } else {
                this.previewIndex++;
            }
        } else {
            this.rotationTimer -= Gdx.graphics.getDeltaTime();
        }
        super.renderCardPreviewInSingleView(sb);
    }

    @Override
    public void update() {
        super.update();
        if (this.dupeListForPrev.isEmpty()) {
            this.dupeListForPrev.addAll(getList());
        }
        if (this.hb.hovered) {
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 1.5F;
                if (this.dupeListForPrev.size() == 0) {
                    this.cardsToPreview = (AbstractCard) CardLibrary.cards.get("Madness");
                } else {
                    this.cardsToPreview = this.dupeListForPrev.get(this.previewIndex);
                }
                if (this.previewIndex == this.dupeListForPrev.size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }
}
