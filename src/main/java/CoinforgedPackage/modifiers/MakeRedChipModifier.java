package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.chips.RedChip;
import basemod.abstracts.AbstractCardModifier;

public class MakeRedChipModifier extends AbstractCardModifier {
    private boolean removeOnCardPlayed;

    public MakeRedChipModifier(boolean removeOnCardPlayed) {
        this.removeOnCardPlayed = removeOnCardPlayed;
    }

    public MakeRedChipModifier() {
        this(true);
    }
    
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {

        return rawDescription + " NL Add a Red Chip into your hand.";
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToTop(new MakeTempCardInHandAction(new RedChip(), 1));
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return removeOnCardPlayed;
    }

    @Override
    public MakeRedChipModifier makeCopy() {
        return new MakeRedChipModifier();
    }
}
