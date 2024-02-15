package Spinwarden.modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.AbstractCardModifier;

public class RetainModifier extends AbstractCardModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (!card.selfRetain) {
            return "Retain. NL " + rawDescription;
        }
        return rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RetainModifier();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (!card.selfRetain) {
            card.selfRetain = true;
            card.retain = true;
        }
    }

}
