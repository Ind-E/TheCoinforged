package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveCardFromDeckAction extends AbstractGameAction {
    private AbstractCard cardToRemove;

    public RemoveCardFromDeckAction(AbstractCard cardToRemove) {
        super();
        this.cardToRemove = cardToRemove;
    }

    public void update() {
        AbstractDungeon.player.drawPile.removeCard(cardToRemove);
        this.isDone = true;
    }
}