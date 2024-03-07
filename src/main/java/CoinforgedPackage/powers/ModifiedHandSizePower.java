package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.BaseMod;

public class ModifiedHandSizePower extends BasePower {
    public static final String POWER_ID = makeID("ModifiedHandSizePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private int hand_size;

    public ModifiedHandSizePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, Math.min(Math.max(amount, -9), 5));
        this.canGoNegative = true;
        this.hand_size = BaseMod.MAX_HAND_SIZE;
    }

    public void updateDescription() {
        if (this.amount < 0) {
            this.description = DESCRIPTIONS[0] + -this.amount + DESCRIPTIONS[1];
            this.type = AbstractPower.PowerType.DEBUFF;
        } else if (this.amount > 0) {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
            this.type = AbstractPower.PowerType.BUFF;
        }

    }

    @Override
    public void onInitialApplication() {
        this.hand_size = BaseMod.MAX_HAND_SIZE;
        if (this.amount < 0) {
            BaseMod.MAX_HAND_SIZE = Math.max(this.hand_size + this.amount, 1);
        } else if (this.amount > 0) {
            BaseMod.MAX_HAND_SIZE = Math.min(this.hand_size + this.amount, 15);
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        if (this.amount + stackAmount > 5) {
            this.amount = 5;
        } else if (this.amount + stackAmount < -9) {
            this.amount = -9;
        } else {
            this.amount += stackAmount;
        }
        if (this.amount < 0) {
            BaseMod.MAX_HAND_SIZE = Math.max(this.hand_size + this.amount, 1);
        } else if (this.amount > 0) {
            BaseMod.MAX_HAND_SIZE = Math.min(this.hand_size + this.amount, 15);
        }
    }

    @Override
    public void onRemove() {
        BaseMod.MAX_HAND_SIZE = this.hand_size;
    }

    @Override
    public void onVictory() {
        BaseMod.MAX_HAND_SIZE = this.hand_size;
    }
}