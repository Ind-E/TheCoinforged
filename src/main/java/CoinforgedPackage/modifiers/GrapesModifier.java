package CoinforgedPackage.modifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.AbstractCardModifier;

public class GrapesModifier extends AbstractCardModifier {
    private int amount;

    public GrapesModifier(int amount) {
        this.amount = amount;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.baseDamage += this.amount;
        card.baseBlock += this.amount;
        card.damage = card.baseDamage;
        card.block = card.baseBlock;
        card.applyPowers();
    }

    @Override
    public GrapesModifier makeCopy() {
        return new GrapesModifier(this.amount);
    }

}
