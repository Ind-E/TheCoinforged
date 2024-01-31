package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FoldAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    private int magic;
    
    public FoldAction(int magic) {
        super();
        this.magic = magic;
    }

    public void update() {
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1;
        for (int i = 0; i < count; i++)
            addToTop(new GainBlockAction(p, magic, true)); 
        this.isDone = true;
    }
}
