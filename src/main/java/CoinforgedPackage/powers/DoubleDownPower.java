package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class DoubleDownPower extends BasePower {
    public static final String POWER_ID = makeID(DoubleDownPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private AbstractPlayer p = AbstractDungeon.player;
    private int magic, baseMagic;

    public DoubleDownPower(AbstractCreature owner, int magic, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.magic = this.baseMagic = magic;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.magic + DESCRIPTIONS[2];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (p.currentBlock <= this.amount) {
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magic, false), this.magic, true,
                            AbstractGameAction.AttackEffect.NONE));
                }
            }
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("DoubleDownPower")));
        }
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        this.magic += baseMagic;
    }

}
