package GamblerMod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.BaseMod;

import static GamblerMod.GamblerMod.makeID;

public class ModifiedHandSizePower extends BasePower {
    public static final String POWER_ID = makeID("ReducedHandSizePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private int hand_size;

    public ModifiedHandSizePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.canGoNegative = true;
        this.hand_size = BaseMod.MAX_HAND_SIZE;
        this.amount = amount;
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
            if (this.hand_size + this.amount >= 1)
                BaseMod.MAX_HAND_SIZE = this.hand_size + this.amount;
            else {
                BaseMod.MAX_HAND_SIZE = 1;
            }
        } else if (this.amount > 0) {
            if (this.hand_size + this.amount <= 20) {
                BaseMod.MAX_HAND_SIZE = this.hand_size + this.amount;
            } else {
                BaseMod.MAX_HAND_SIZE = 20;
            }
            BaseMod.MAX_HAND_SIZE = this.hand_size + this.amount;
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount < 0) {
            this.hand_size = BaseMod.MAX_HAND_SIZE;
            if (this.hand_size + this.amount >= 1)
                BaseMod.MAX_HAND_SIZE = this.hand_size + this.amount;
            else {
                BaseMod.MAX_HAND_SIZE = 1;
            }
        } else if (this.amount > 0) {
            this.hand_size = BaseMod.MAX_HAND_SIZE;
            if (this.hand_size + this.amount <= 20) {
                BaseMod.MAX_HAND_SIZE = this.hand_size + this.amount;
            } else {
                BaseMod.MAX_HAND_SIZE = 20;
            }
            BaseMod.MAX_HAND_SIZE = this.hand_size + this.amount;
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