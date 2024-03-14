package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class RollRed extends AbstractMultiPreviewCard {
    private static final int DICE_TO_ROLL = 2;
    private static final int UPG_DICE_TO_ROLL = 1;

    public float rotationTimer;
    public int previewIndex;
    public ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(RollRed.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            1);

    public RollRed() {
        super(ID, info);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
        this.cardsToPreview = CardLibrary.cards.get("Bash");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollRedAction(p, this.magicNumber));
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

    public ArrayList<CardTags> getTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new RollRed();
    }
}
