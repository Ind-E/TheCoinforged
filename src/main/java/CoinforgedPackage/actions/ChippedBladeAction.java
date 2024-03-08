package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChippedBladeAction extends AbstractGameAction {
    AbstractMonster m;

    public ChippedBladeAction(AbstractMonster m) {
        super();
        this.m = m;
    }

    public void update() {
        int chipsToAdd = 0;
        for (AbstractPower power : m.powers)
            if (power.type == AbstractPower.PowerType.DEBUFF)
                chipsToAdd++;
        addToBot(new MakeChipsInHandAction(chipsToAdd));
        this.isDone = true;
    }
}