package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.modifiers.RedKingModifier;
import CoinforgedPackage.powers.RedKingPower;
import CoinforgedPackage.util.CardStats;
import basemod.helpers.CardModifierManager;

public class RedKing extends BaseCard {
    private static final int DICE_TO_ROLL = 2;
    private static final int UPG_DICE_TO_ROLL = 2;

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(RedKing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public RedKing() {
        super(ID, info);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(RedKing.ID))
            addToBot(new ApplyPowerAction(p, p, new RedKingPower(p)));
        addToBot(new RollRedAction(p, this.magicNumber));
        for (AbstractCard card : p.hand.group) {
            if (card.hasTag(CustomTags.RED_DIE)) {
                CardModifierManager.addModifier(card, new RedKingModifier());
            }
        }

        for (AbstractCard card : p.drawPile.group) {
            if (card.hasTag(CustomTags.RED_DIE)) {
                CardModifierManager.addModifier(card, new RedKingModifier());
            }
        }

        for (AbstractCard card : p.discardPile.group) {
            if (card.hasTag(CustomTags.RED_DIE)) {
                CardModifierManager.addModifier(card, new RedKingModifier());
            }
        }

        for (AbstractCard card : p.exhaustPile.group) {
            if (card.hasTag(CustomTags.RED_DIE)) {
                CardModifierManager.addModifier(card, new RedKingModifier());
            }
        }
    }

    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> myList = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q.hasTag(CustomTags.RED_DIE) && q.damage <= 6) {
                AbstractCard r = q.makeCopy();
                myList.add(r);
            }
        }
        return myList;
    }

    public void update() {
        super.update();
        if (this.dupeListForPrev.isEmpty())
            this.dupeListForPrev.addAll(getList());
        if (this.hb.hovered)
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

    @Override
    public AbstractCard makeCopy() {
        return new RedKing();
    }
}
