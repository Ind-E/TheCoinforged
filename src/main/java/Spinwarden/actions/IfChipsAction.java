package Spinwarden.actions;

import static Spinwarden.util.GeneralUtils.getNumChips;

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
            addToBot(action);
            addToBot(new SpendChipsAction(chips));
        }
        this.isDone = true;
    }
}