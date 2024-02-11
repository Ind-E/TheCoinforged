package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import GamblerMod.GamblerMod;

public class RigAction extends AbstractGameAction{
    private AbstractPlayer p = AbstractDungeon.player;

    public RigAction() {
        super();
    }
    
    public void update() {
        for (AbstractCard c : p.drawPile.group) {
            if (!c.freeToPlayOnce) {
                c.freeToPlayOnce = true;
                c.tags.add(GamblerMod.RIGGED);
            }
        }
        for (AbstractCard c : p.discardPile.group) {
            if (!c.freeToPlayOnce) {
                c.freeToPlayOnce = true;
                c.tags.add(GamblerMod.RIGGED);
            }
        }
        this.isDone = true;
    }
}