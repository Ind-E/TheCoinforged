package CoinforgedPackage.actions;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class IfChipsAction extends AbstractGameAction {
    private AbstractGameAction action;
    private int chips;

    public IfChipsAction(int chips, AbstractGameAction action) {
        super();
        this.action = action;
        this.chips = chips;
    }

    public void update() {
        if (getNumChips() >= chips) {
            addToTop(action);
            addToTop(new SpendChipsAction(chips));
        }
        this.isDone = true;
    }
}