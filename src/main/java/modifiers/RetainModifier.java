package modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.AbstractCardModifier;

public class RetainModifier extends AbstractCardModifier{


    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "Retain. NL " + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RetainModifier();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.selfRetain = true;
        card.retain = true;
    }
    
}
