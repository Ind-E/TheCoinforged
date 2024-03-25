package CoinforgedPackage.modifiers;

import static CoinforgedPackage.util.GeneralUtils.makeKeyword;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.chips.GreenChip;
import basemod.abstracts.AbstractCardModifier;

public class MakeGreenChipModifier extends AbstractCardModifier {
    private boolean removeOnCardPlayed;

    public MakeGreenChipModifier() {
        this(true);
    }

    public MakeGreenChipModifier(boolean removeOnCardPlayed) {
        this.removeOnCardPlayed = removeOnCardPlayed;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {

        return rawDescription + " NL Add a " + makeKeyword("Green_Chip") + " into your hand.";
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToTop(new MakeTempCardInHandAction(new GreenChip(), 1));
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return removeOnCardPlayed;
    }

    @Override
    public MakeGreenChipModifier makeCopy() {
        return new MakeGreenChipModifier();
    }
}
