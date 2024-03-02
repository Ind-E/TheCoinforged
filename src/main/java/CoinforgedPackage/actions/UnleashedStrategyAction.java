package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class UnleashedStrategyAction extends AbstractGameAction {
    public UnleashedStrategyAction() {
        super();
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        int str = p.hand.size() / 2;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, str), str));
        this.isDone = true;
    }
}