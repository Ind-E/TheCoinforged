package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DrawbackPlayerPower extends BasePower {
    public static final String POWER_ID = makeID(DrawbackPlayerPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DrawbackPlayerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        addToTop(new DrawCardAction(1));
        if (!(info.name != null && info.name.equals("Coinforged_Orange"))) {
            addToTop(new ReducePowerAction(this.owner, this.owner, DrawbackPlayerPower.POWER_ID, 1));
        }
        return damageAmount;
    }
}