package CoinforgedPackage.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import CoinforgedPackage.cards.CustomTags;

public class ChipShuffleAction extends AbstractGameAction {

    public ChipShuffleAction() {
        super();
    }

    public void update() {
        int cardsToDraw = 0;
        CardGroup hand = AbstractDungeon.player.hand;
        ArrayList<AbstractCard> cardsToMove = new ArrayList<>();
        
        for (AbstractCard c : hand.group) {
            if (c.hasTag(CustomTags.POKER_CHIP)) {
                cardsToMove.add(c);
                cardsToDraw++;
            }
        }

        for (AbstractCard c : cardsToMove) {
            hand.moveToDeck(c, true);
        }
        addToBot(new DrawCardAction(cardsToDraw));
        this.isDone = true;
    }
}