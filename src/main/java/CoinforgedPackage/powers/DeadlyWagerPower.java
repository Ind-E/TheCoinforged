package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMod.makeID;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DeadlyWagerPower extends BasePower {
    public static final String POWER_ID = makeID(DeadlyWagerPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private AbstractPlayer p = AbstractDungeon.player;

    public DeadlyWagerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new LoseHPAction(p, p, this.amount));
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

}
