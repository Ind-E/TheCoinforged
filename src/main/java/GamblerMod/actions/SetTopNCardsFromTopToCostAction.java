package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

public class SetTopNCardsFromTopToCostAction extends AbstractGameAction {
    private CardGroup group;
    private int amount;
    private int cost;

    public SetTopNCardsFromTopToCostAction(CardGroup group, int amount, int cost) {
        this.group = group;
        this.amount = amount;
        this.cost = cost;
    }

    public void update() {
        AbstractCard c;
        for (int i = 0; i < amount; i++) {
            System.out.println("i: " + i + " group.size: " + group.size());
            if (i < group.size()) {
                c = group.getNCardFromTop(i);
                c.setCostForTurn(cost);
            }
        }
        this.isDone = true;
    }
}