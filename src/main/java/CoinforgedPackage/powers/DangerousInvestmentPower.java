package CoinforgedPackage.powers;

import java.util.Collections;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DangerousInvestmentPower extends BasePower {
    public static final String POWER_ID = makeID("DangerousInvestmentPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public DangerousInvestmentPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        String energy = String.join(" ", Collections.nCopies(this.amount, "[E]"));
        this.description = String.format("%s %s ", DESCRIPTIONS[0], energy) + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfRound() {
        addToBot(new GainEnergyAction(this.amount));
    }
}