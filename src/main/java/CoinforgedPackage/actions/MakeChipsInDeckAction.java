package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import CoinforgedPackage.cards.tempCards.PokerChip;

public class MakeChipsInDeckAction extends AbstractGameAction {
    private int chipsToMake;

    public MakeChipsInDeckAction(int chipsToMake) {
        super();
        this.chipsToMake = chipsToMake;
    }

    public void update() {
        addToBot(new MakeTempCardInDrawPileAction(new PokerChip(), chipsToMake, false, true));
        this.isDone = true;
    }
}