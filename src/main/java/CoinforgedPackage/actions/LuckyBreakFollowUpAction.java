package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class LuckyBreakFollowUpAction extends AbstractGameAction {

    private int magic = 0;
    private AbstractPlayer p = AbstractDungeon.player;

    public LuckyBreakFollowUpAction(int magic) {
        this.magic = magic;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
        tickDuration();
        if (this.isDone) {
            int cost = p.hand.getTopCard().costForTurn;
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if (c.costForTurn != cost) {
                    return;
                }
            }
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magic)));
        }
        
    }

}
