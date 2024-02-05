package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public abstract class RollBaseAction extends AbstractGameAction{
    private int minroll = 1;
    private int maxroll = 6;
    private int magic = 0;
    private AbstractPlayer player;

    public RollBaseAction(AbstractCreature owner, int magic) {
        this.player = AbstractDungeon.player;
        this.magic = magic;
    }
    public RollBaseAction(AbstractCreature owner, int magic, int minroll, int maxroll) {
        this.player = AbstractDungeon.player;
        this.magic = magic;
        this.minroll = minroll;
        this.maxroll = maxroll;
    }

    public AbstractCard roll() {
        return null;
    }

    public void update() {
        for (int i = 0; i < magic; i++) {
            addToBot(new MakeTempCardInHandAction(roll(), 1));
        }
        this.isDone = true;
    }
}
