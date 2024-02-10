package GamblerMod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.BaseMod;

import static GamblerMod.GamblerMod.makeID;

public class ReducedHandSizePower extends BasePower{
    public static final String POWER_ID = makeID("ReducedHandSizePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private int hand_size;

    public ReducedHandSizePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.canGoNegative = true;
        this.hand_size = BaseMod.MAX_HAND_SIZE;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + -this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onInitialApplication() {
        this.hand_size = BaseMod.MAX_HAND_SIZE;
        if (this.hand_size + this.amount >= 1)
            BaseMod.MAX_HAND_SIZE = this.hand_size + this.amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.hand_size + this.amount >= 1)
            BaseMod.MAX_HAND_SIZE = this.hand_size + this.amount;
    }

    @Override
    public void onVictory() {
        BaseMod.MAX_HAND_SIZE = this.hand_size;
    }
}