package Spinwarden.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import Spinwarden.cards.tempCards.PokerChip;

public class MakeChipsInHandAction extends AbstractGameAction {
    private int chipsToMake;

    public MakeChipsInHandAction(int chipsToMake) {
        super();
        this.chipsToMake = chipsToMake;
    }

    public void update() {
        addToBot(new MakeTempCardInHandAction(new PokerChip(), chipsToMake));
        this.isDone = true;
    }
}