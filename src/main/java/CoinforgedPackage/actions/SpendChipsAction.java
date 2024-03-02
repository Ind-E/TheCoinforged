package CoinforgedPackage.actions;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.CoinforgedMod;

public class SpendChipsAction extends AbstractGameAction {
    private int chips;

    public SpendChipsAction(int chips) {
        super();
        this.chips = chips;
    }

    public void update() {
        int chipsDiscarded = 0;
        if (getNumChips() >= chips) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(CoinforgedMod.POKER_CHIP)) {
                    addToBot(new DiscardSpecificCardAction(c));
                    chipsDiscarded++;
                    if (chipsDiscarded >= chips) {
                        break;
                    }
                }
            }
        } else {
            throw (new IndexOutOfBoundsException("not enough chips"));
        }
        this.isDone = true;
    }
}