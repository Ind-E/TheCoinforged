package GamblerMod.actions;

import static GamblerMod.GamblerMod.makeID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import GamblerMod.cards.tempCards.RedEight;
import GamblerMod.cards.tempCards.RedFive;
import GamblerMod.cards.tempCards.RedFour;
import GamblerMod.cards.tempCards.RedNine;
import GamblerMod.cards.tempCards.RedOne;
import GamblerMod.cards.tempCards.RedSeven;
import GamblerMod.cards.tempCards.RedSix;
import GamblerMod.cards.tempCards.RedTen;
import GamblerMod.cards.tempCards.RedThree;
import GamblerMod.cards.tempCards.RedTwo;
import GamblerMod.powers.AdvantagePower;


public class RollRedAction extends AbstractGameAction{
    private int minroll = 1;
    private int maxroll = 6;
    private int magic = 0;
    private AbstractPlayer player;

    public RollRedAction(AbstractCreature owner, int magic) {
        this.player = AbstractDungeon.player;
        this.magic = magic;
    }
    public RollRedAction(AbstractCreature owner, int magic, int minroll, int maxroll) {
        this.player = AbstractDungeon.player;
        this.magic = magic;
        this.minroll = minroll;
        this.maxroll = maxroll;
    }

    public AbstractCard roll() {
        int dmg;
        if (player.hasPower(AdvantagePower.POWER_ID)) {
            int adv = player.getPower(AdvantagePower.POWER_ID).amount;
            ArrayList<Integer> rolls = new ArrayList<Integer>();
            for (int i = 0; i < adv; i++) {
                rolls.add(ThreadLocalRandom.current().nextInt(minroll, maxroll + 1));
            }
            dmg = Collections.max(rolls);
        
        } else {
            dmg = ThreadLocalRandom.current().nextInt(minroll, maxroll + 1);
        }
        
        AbstractCard cardToAdd = null;
        switch(dmg) {
            case 1:
                cardToAdd = new RedOne();
                break;
            case 2:
                cardToAdd = new RedTwo();
                break;
            case 3:
                cardToAdd = new RedThree();
                break;
            case 4:
                cardToAdd = new RedFour();
                break;
            case 5:
                cardToAdd = new RedFive();
                break;
            case 6:
                cardToAdd = new RedSix();
                break;
            case 7:
                cardToAdd = new RedSeven();   
                break;
            case 8:
                cardToAdd = new RedEight();   
                break;
            case 9:
                cardToAdd = new RedNine();   
                break;
            case 10 :
                cardToAdd = new RedTen();
                break;
            default:
                cardToAdd = new RedTen(dmg);   
                break;
        }
        return cardToAdd;
    }

    public void update() {
        for (int i = 0; i < magic; i++) {
            addToBot(new MakeTempCardInHandAction(roll(), 1));
        }
        
        this.isDone = true;
    }
}
