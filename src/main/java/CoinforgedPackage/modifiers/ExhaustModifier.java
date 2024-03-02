package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.AbstractCardModifier;

public class ExhaustModifier extends AbstractCardModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (!card.exhaust) {
            return rawDescription + " NL Exhaust.";
        }
        return rawDescription;
    }

    public ExhaustModifier makeCopy() {
        return new ExhaustModifier();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (!card.exhaust) {
            card.exhaust = true;
        }
    }

}
