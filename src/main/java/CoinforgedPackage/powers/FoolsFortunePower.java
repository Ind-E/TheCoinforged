package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;

public class FoolsFortunePower extends BasePower {
    public static final String POWER_ID = makeID(FoolsFortunePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int magic;
    private AbstractPlayer p = AbstractDungeon.player;

    public FoolsFortunePower(AbstractCreature owner, int magic, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.magic = magic;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer)
            return;
        for (AbstractCard c : p.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK)
                return;
        }
        addToBot(new GainBlockAction(p, this.amount));
        addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, magic)));
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }
}
