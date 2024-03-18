package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SharksEmbracePower extends BasePower {
    public static final String POWER_ID = makeID(SharksEmbracePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SharksEmbracePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            if (!AbstractDungeon.player.hand.isEmpty()) {
                AbstractCard leftmostCard = AbstractDungeon.player.hand.getBottomCard();
                addToBot(new DiscardSpecificCardAction(leftmostCard, AbstractDungeon.player.hand));
            }
            addToBot(new DrawCardAction(this.owner, this.amount));
        }
    }
}