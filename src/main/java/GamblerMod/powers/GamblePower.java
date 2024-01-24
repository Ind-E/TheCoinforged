package GamblerMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static GamblerMod.GamblerMod.makeID;

public class GamblePower extends BasePower{
    public static final String POWER_ID = makeID("GamblePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public GamblePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.amount = amount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void atStartOfTurn() {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("GamblePower")));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new GoldIfWinPower(this.owner, this.amount)));
    }
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

}
