package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMod.makeID;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CripplingDebtPower extends BasePower implements HealthBarRenderPower {
    public static final String POWER_ID = makeID(CripplingDebtPower.class.getSimpleName());

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public CripplingDebtPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public int onLoseHp(int damageAmount) {
        checkInstantKill();
        return damageAmount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flashWithoutSound();
        this.stackPower((int) Math.max(1, Math.round(this.amount * 0.25)));
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
        checkInstantKill();
    }

    @Override
    public void onInitialApplication() {
        checkInstantKill();
    }

    @Override
    public void onDeath() {
        addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true),
                DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    public void checkInstantKill() {
        if (this.owner.currentHealth > 0 && this.owner.currentHealth <= this.amount) {
            addToBot(new InstantKillAction(this.owner));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, CripplingDebtPower.POWER_ID));
        }
    }

    @Override
    public Color getColor() {
        return Color.SKY;
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }
}
