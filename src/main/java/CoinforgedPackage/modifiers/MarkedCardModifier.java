package CoinforgedPackage.modifiers;

import static CoinforgedPackage.util.GeneralUtils.makeKeyword;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.CustomTags;
import basemod.abstracts.AbstractCardModifier;

public class MarkedCardModifier extends AbstractCardModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (!card.hasTag(CustomTags.MARKED)) {
            return rawDescription;
        }
        return makeKeyword("Marked") + ". NL " + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MarkedCardModifier();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (!card.hasTag(CustomTags.MARKED)) {
            card.tags.add(CustomTags.MARKED);
        }
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new GainEnergyAction(1));
        addToBot(new DrawCardAction(1));
    }

    public Color getGlow(AbstractCard card) {
        return Color.MAGENTA.cpy();
    }

}
