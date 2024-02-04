package GamblerMod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static GamblerMod.GamblerMod.makeID;

public class PokerFacePower extends BasePower{
    public static final String POWER_ID = makeID("PokerFacePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int hp;

    public PokerFacePower(AbstractCreature owner, int hp) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 2);
        this.hp = hp;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            AbstractDungeon.player.currentHealth = hp;
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        } 
    }
}
