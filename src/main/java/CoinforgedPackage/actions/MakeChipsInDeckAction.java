package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import CoinforgedPackage.cards.tempCards.PokerChip;

public class MakeChipsInDeckAction extends AbstractGameAction {
    private int chipsToMake;
    private boolean shuffleInto;

    public MakeChipsInDeckAction(int chipsToMake, boolean shuffleInto) {
        super();
        this.chipsToMake = chipsToMake;
        this.shuffleInto = shuffleInto;
    }

    public void update() {
        addToBot(new MakeTempCardInDrawPileAction(new PokerChip(), chipsToMake, shuffleInto, true));
        this.isDone = true;
    }
}