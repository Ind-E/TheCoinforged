package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

import CoinforgedPackage.cards.chips.WhiteChip;

public class MakeChipsInHandAction extends AbstractGameAction {
    private int chipsToMake;

    public MakeChipsInHandAction(int chipsToMake) {
        super();
        this.chipsToMake = chipsToMake;
    }

    public void update() {
        addToTop(new MakeTempCardInHandAction(new WhiteChip(), chipsToMake));
        this.isDone = true;
    }
}