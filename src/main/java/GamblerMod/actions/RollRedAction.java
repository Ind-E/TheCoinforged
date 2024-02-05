package GamblerMod.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
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
import GamblerMod.powers.RedKingPower;
import GamblerMod.powers.SnakeEyesPower;


public class RollRedAction extends RollBaseAction{
    private boolean redKingActive = false;

    public RollRedAction(AbstractCreature owner, int magic) {
        super(owner, magic);
    }
    public RollRedAction(AbstractCreature owner, int magic, int minroll, int maxroll) {
        super(owner, magic, minroll, maxroll);
    }

    @Override
    public AbstractCard roll() {
        int dmg;
        if (player.hasPower(SnakeEyesPower.POWER_ID)) {
            dmg = 1;
        }
        else if (player.hasPower(AdvantagePower.POWER_ID)) {
            int adv = player.getPower(AdvantagePower.POWER_ID).amount;
            ArrayList<Integer> rolls = new ArrayList<Integer>();
            for (int i = 0; i < adv; i++) {
                rolls.add(ThreadLocalRandom.current().nextInt(minroll, maxroll + 1));
            }
            dmg = Collections.max(rolls);
        
        } else {
            dmg = ThreadLocalRandom.current().nextInt(minroll, maxroll + 1);
        }

        if (player.hasPower(RedKingPower.POWER_ID)) {
            redKingActive = true;   
        }

        AbstractCard cardToAdd = null;
        switch(dmg) {
            case 1:
                cardToAdd = redKingActive ? new RedOne(true) : new RedOne();  
                break;
            case 2:
                cardToAdd = redKingActive ? new RedTwo(true) : new RedTwo();  
                break;
            case 3:
                cardToAdd = redKingActive ? new RedThree(true) : new RedThree();  
                break;
            case 4:
                cardToAdd = redKingActive ? new RedFour(true) : new RedFour();  
                break;
            case 5:
                cardToAdd = redKingActive ? new RedFive(true) : new RedFive();  
                break;
            case 6:
                cardToAdd = redKingActive ? new RedSix(true) : new RedSix();  
                break;
            case 7:
                cardToAdd = redKingActive ? new RedSeven(true) : new RedSeven();  
                break;
            case 8:
                cardToAdd = redKingActive ? new RedEight(true) : new RedEight();  
                break;
            case 9:
                cardToAdd = redKingActive ? new RedNine(true) : new RedNine();  
                break;
            case 10 :
                cardToAdd = redKingActive ? new RedTen(true) : new RedTen();  
                break;
            default:
                cardToAdd = redKingActive ? new RedTen(dmg, true) : new RedTen(dmg);  
                break;
        }
        return cardToAdd;
    }
}
