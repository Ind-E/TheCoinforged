package Spinwarden.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Spinwarden.modifiers.MarkedCardModifier;
import basemod.helpers.CardModifierManager;

public class CasinoCatalystAction extends AbstractGameAction {

    public void update() {
        AbstractCard randomCard = AbstractDungeon.player.hand.getRandomCard(true);
        if (randomCard != null) {
            CardModifierManager.addModifier(randomCard, new MarkedCardModifier());
            this.isDone = true;
        }
    }
}