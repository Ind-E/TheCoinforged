package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;

import CoinforgedPackage.cards.tempCards.RedDieBase;
import basemod.abstracts.AbstractCardModifier;

public class RedKingModifier extends AbstractCardModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "Deal !D! damage to ALL enemies. NL Exhaust.";
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RedKingModifier();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (card instanceof RedDieBase) {
            card.target = CardTarget.ALL_ENEMY;
            ((RedDieBase) card).setMultiDamage(true);
            card.applyPowers();
        }
    }

}
