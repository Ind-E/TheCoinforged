package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class FatesHandAction extends AbstractGameAction{

    private int magic = 0;
    private AbstractPlayer p = AbstractDungeon.player;

    public FatesHandAction(int magic) {
        this.magic = magic;
    }

    public void update() {
        if (this.p.discardPile.getTopCard().costForTurn == p.discardPile.getNCardFromTop(1).costForTurn) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magic)));
        }
        this.isDone = true;
    }

}
