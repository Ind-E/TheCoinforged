package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import GamblerMod.modifiers.MarkedCardModifier;
import basemod.helpers.CardModifierManager;

public class CasinoCatalystAction extends AbstractGameAction {

    public void update() {
        AbstractCard randomCard = AbstractDungeon.player.hand.getRandomCard(true);
        if (randomCard != null) {
            CardModifierManager.addModifier(randomCard, new MarkedCardModifier());
            this.isDone = true;
            System.out.println("Marked card: " + randomCard.name);
        }
    }
}