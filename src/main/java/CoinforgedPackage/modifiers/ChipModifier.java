package CoinforgedPackage.modifiers;

import static CoinforgedPackage.util.GeneralUtils.makeKeyword;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;
import CoinforgedPackage.cards.chips.BaseChip;
import CoinforgedPackage.cards.chips.BlackChip;
import CoinforgedPackage.cards.chips.BlueChip;
import CoinforgedPackage.cards.chips.CrackedChip;
import CoinforgedPackage.cards.chips.GrayChip;
import CoinforgedPackage.cards.chips.GreenChip;
import CoinforgedPackage.cards.chips.OrangeChip;
import CoinforgedPackage.cards.chips.RedChip;
import CoinforgedPackage.cards.chips.WhiteChip;
import basemod.abstracts.AbstractCardModifier;

public class ChipModifier extends AbstractCardModifier {
    private boolean removeOnCardPlayed;
    private AbstractCard prevCardsToPreview;
    private ChipColor color;

    public ChipModifier(ChipColor color, boolean removeOnCardPlayed) {
        this.removeOnCardPlayed = removeOnCardPlayed;
        this.color = color;
    }

    public ChipModifier(ChipColor color) {
        this(color, true);
    }

    public BaseChip colorToChip(ChipColor color) {
        switch (color) {
            case Black:
                return new BlackChip();
            case Blue:
                return new BlueChip();
            case Cracked:
                return new CrackedChip();
            case Gray:
                return new GrayChip();
            case Green:
                return new GreenChip();
            case Orange:
                return new OrangeChip();
            case Red:
                return new RedChip();
            case White:
                return new WhiteChip();
            default:
                return null;
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL Add a " + makeKeyword(color.toString() + "_Chip") + " into your hand.";
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToTop(new MakeTempCardInHandAction(colorToChip(color), 1));
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return removeOnCardPlayed;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        prevCardsToPreview = card.cardsToPreview;
        if (prevCardsToPreview == null) {
            card.cardsToPreview = colorToChip(color);
        }
    }

    @Override
    public void onRemove(AbstractCard card) {
        card.cardsToPreview = prevCardsToPreview;
    }

    @Override
    public ChipModifier makeCopy() {
        return new ChipModifier(color, this.removeOnCardPlayed);
    }
}
