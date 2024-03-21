package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.chips.CrackedChip;
import basemod.abstracts.AbstractCardModifier;

public class MakeCrackedChipModifier extends AbstractCardModifier {
    private boolean removeOnCardPlayed;

    public MakeCrackedChipModifier() {
        this(true);
    }

    public MakeCrackedChipModifier(boolean removeOnCardPlayed) {
        this.removeOnCardPlayed = removeOnCardPlayed;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {

        return rawDescription + " NL Add a Cracked Chip into your hand.";
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToTop(new MakeTempCardInHandAction(new CrackedChip(), 1));
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return removeOnCardPlayed;
    }

    @Override
    public MakeCrackedChipModifier makeCopy() {
        return new MakeCrackedChipModifier();
    }
}
