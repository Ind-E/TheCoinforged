package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMod.makeID;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.actions.ChaosFormAction;

public class ChaosFormPower extends BasePower {
    public static final String POWER_ID = makeID(ChaosFormPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ChaosFormPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }

    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < this.amount; i++) {
            addToBot(new ChaosFormAction(AbstractDungeon.player));
        }
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

}
