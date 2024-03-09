package CoinforgedPackage.actions;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.CustomTags;

public class SpendChipsAction extends AbstractGameAction {
    private int chips;
    private boolean exhaust = false;

    public SpendChipsAction(int chips) {
        super();
        this.chips = chips;
    }

    public SpendChipsAction(int chips, boolean exhaust) {
        this(chips);
        this.exhaust = exhaust;
    }

    public void update() {
        int chipsSpent = 0;
        if (getNumChips() >= chips) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(CustomTags.POKER_CHIP)) {
                    if (exhaust) {
                        addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                    } else {
                        addToBot(new DiscardSpecificCardAction(c, AbstractDungeon.player.hand));
                    }
                    chipsSpent++;
                    if (chipsSpent >= chips) {
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