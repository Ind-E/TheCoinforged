package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class LuckyBreakAction extends AbstractGameAction {

    private int magic = 0;
    private AbstractPlayer p = AbstractDungeon.player;

    public LuckyBreakAction(int magic) {
        this.magic = magic;
    }

    public void update() {
        if (this.p.hand.getTopCard().costForTurn == p.hand.getNCardFromTop(1).costForTurn) {
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magic)));
        }
        this.isDone = true;
    }

}
