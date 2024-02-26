package Spinwarden.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
// import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Spinwarden.SpinwardenMain.makeID;

public class LongGamePower extends BasePower implements NonStackablePower {
    public static final String POWER_ID = makeID("LongGamePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private AbstractCard card;

    public LongGamePower(AbstractCreature owner, int amount, AbstractCard card) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.card = card;
        updateDescription();
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
        if (this.amount == 1) {
            this.addToBot(new MakeTempCardInHandAction(card));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.source, this.ID));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }
}