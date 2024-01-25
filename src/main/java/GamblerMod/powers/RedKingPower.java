package GamblerMod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static GamblerMod.GamblerMod.makeID;

public class RedKingPower extends BasePower{
    public static final String POWER_ID = makeID("RedKingPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public RedKingPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
