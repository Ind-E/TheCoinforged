package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

import CoinforgedPackage.cards.tempCards.PokerChip;

public class MakeChipsInHandAction extends AbstractGameAction {
    private int chipsToMake;

    public MakeChipsInHandAction(int chipsToMake) {
        super();
        this.chipsToMake = chipsToMake;
    }

    public void update() {
        addToTop(new MakeTempCardInHandAction(new PokerChip(), chipsToMake));
        this.isDone = true;
    }
}