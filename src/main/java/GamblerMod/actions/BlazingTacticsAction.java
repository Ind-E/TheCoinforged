package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class BlazingTacticsAction extends AbstractGameAction{
    private AbstractCard targetCard;
    private int magic;

    public BlazingTacticsAction(AbstractCard targetCard, int magic) {
        super();
        this.targetCard = targetCard;
        this.magic = magic;
    }

    public void update() {
        if (EnergyPanel.totalCount > AbstractDungeon.player.hand.size() - 1) {
            addToBot(new DrawCardAction(AbstractDungeon.player, magic));
        } else {
            this.addToBot(new ExhaustSpecificCardAction(targetCard, AbstractDungeon.player.hand));
            this.addToBot(new ExhaustSpecificCardAction(targetCard, AbstractDungeon.player.discardPile));
        }
        this.isDone = true;
    }
}
