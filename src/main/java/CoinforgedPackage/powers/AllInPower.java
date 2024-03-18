package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;

public class AllInPower extends BasePower {
    public static final String POWER_ID = makeID(AllInPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public AllInPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("AllInPower")));
        addToBot(new ApplyPowerAction(this.owner, this.owner, new NoBlockPower(this.owner, 1, false), 1));
    }

}
