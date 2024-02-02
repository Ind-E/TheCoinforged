package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HeartOfTheCardsAction extends AbstractGameAction{

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
           c.setCostForTurn(0); 
        }
        this.isDone = true;
    }

}
