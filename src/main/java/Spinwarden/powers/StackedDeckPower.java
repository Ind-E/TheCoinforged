package Spinwarden.powers;

import static Spinwarden.SpinwardenMain.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import Spinwarden.SpinwardenMain;

public class StackedDeckPower extends BasePower {
    public static final String POWER_ID = makeID("StackedDeckPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private AbstractPlayer p = AbstractDungeon.player;

    public StackedDeckPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (int i = 0; i < this.amount; i++) {
            stack();
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }

    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    private void stack() {
        boolean possible = false;
        for (AbstractCard c : this.p.drawPile.group) {
            if (c.cost > 0 && c.freeToPlayOnce == false)
                possible = true;
        }
        if (possible)
            findAndModifyCard();
    }

    private void findAndModifyCard() {
        AbstractCard c = this.p.drawPile.getRandomCard(AbstractDungeon.cardRandomRng);
        if (c.cost > 0 && c.freeToPlayOnce == false) {
            c.freeToPlayOnce = true;
        } else {
            findAndModifyCard();
        }
    }

}
