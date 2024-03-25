package CoinforgedPackage.modifiers;

import static CoinforgedPackage.util.GeneralUtils.makeKeyword;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.chips.BlueChip;
import basemod.abstracts.AbstractCardModifier;

public class MakeBlueChipModifier extends AbstractCardModifier {
    private boolean removeOnCardPlayed;

    public MakeBlueChipModifier() {
        this(true);
    }

    public MakeBlueChipModifier(boolean removeOnCardPlayed) {
        this.removeOnCardPlayed = removeOnCardPlayed;
    }
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {

        return rawDescription + " NL Add a " + makeKeyword("Blue_Chip") + " into your hand.";
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToTop(new MakeTempCardInHandAction(new BlueChip(), 1));
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return removeOnCardPlayed;
    }

    @Override
    public MakeBlueChipModifier makeCopy() {
        return new MakeBlueChipModifier();
    }
}
