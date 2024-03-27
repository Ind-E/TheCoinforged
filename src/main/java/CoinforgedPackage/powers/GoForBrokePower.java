package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GoForBrokePower extends BasePower {
    public static final String POWER_ID = makeID(GoForBrokePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int increase;
    private int reset;

    public GoForBrokePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.increase = 1;
        this.reset = amount;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.increase + DESCRIPTIONS[2]
                + this.reset + DESCRIPTIONS[3];
    }

    @Override
    public void atStartOfTurn() {
        this.amount = reset;
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (this.amount > 0) {
            AbstractMonster r = AbstractDungeon.getMonsters().getRandomMonster(null, true,
                    AbstractDungeon.cardRandomRng);
            if (r != null && !r.isDeadOrEscaped()) {
                addToBot(new ApplyPowerAction(r, AbstractDungeon.player, new CripplingDebtPower(r, this.amount),
                        this.amount));
            }
        }
        this.amount += increase;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.increase += Math.max(1, stackAmount);
        updateDescription();
    }
}
