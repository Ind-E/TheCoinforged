package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.chips.WhiteChip;
import basemod.abstracts.AbstractCardModifier;

public class MakeWhiteChipModifier extends AbstractCardModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {

        return rawDescription + " NL Add a White Chip into your hand.";
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToTop(new MakeTempCardInHandAction(new WhiteChip(), 1));
    }

    @Override
    public MakeWhiteChipModifier makeCopy() {
        return new MakeWhiteChipModifier();
    }
}
