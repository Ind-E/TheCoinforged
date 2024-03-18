package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PokerFacePower extends BasePower {
    public static final String POWER_ID = makeID(PokerFacePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private int hp;
    private AbstractPlayer p = AbstractDungeon.player;

    public PokerFacePower(AbstractCreature owner, int hp) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 2);
        this.hp = hp;
        updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.hp + ".";
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2] + this.hp + ".";
        }

    }

    @Override
    public void atStartOfTurn() {
        if (this.amount == 1) {
            p.currentHealth = hp;
            p.healthBarUpdatedEvent();
            // int hpToHeal = hp - p.currentHealth;
            // if (hpToHeal > 0)
            // p.heal(hpToHeal);
            // else if (hpToHeal < 0)
            // addToBot(new DamageAction(p, new DamageInfo(p, -hpToHeal,
            // DamageType.HP_LOSS)));
        }
        addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));

    }

    @Override
    public void stackPower(int stackAmount) {
        addToBot(new ApplyPowerAction(owner, source, null));
    }
}
