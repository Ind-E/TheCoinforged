package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMod.makeID;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

//TODO: damage to all affected by weak on player, onAttacked happens before monster loses health
public class CripplingDebtPower extends BasePower implements HealthBarRenderPower {
    public static final String POWER_ID = makeID("CripplingDebtPower");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public CripplingDebtPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public int onLoseHp(int damageAmount) {
        checkExplode();
        return damageAmount;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner != this.owner && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
            checkExplode();
        }
        return damageAmount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flashWithoutSound();
        this.stackPower(1);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
        checkExplode();
    }

    @Override
    public void onInitialApplication() {
        checkExplode();
    }

    public void checkExplode() {
        if (this.owner.currentHealth > 0 && this.owner.currentHealth <= this.amount) {
            addToBot(new DamageAllEnemiesAction(null, this.amount, DamageType.THORNS,
                    AttackEffect.FIRE));
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
