package GamblerMod.actions;

import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import GamblerMod.cards.tempCards.ChaosOne;
import GamblerMod.cards.tempCards.ChaosSix;
import GamblerMod.cards.tempCards.ChaosTwo;
import GamblerMod.cards.tempCards.ChaosThree;
import GamblerMod.cards.tempCards.ChaosFive;
import GamblerMod.cards.tempCards.ChaosFour;


public class ChaosFormAction extends AbstractGameAction{

    public ChaosFormAction() {}

    public AbstractCard roll() {
        int chaosRoll = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        AbstractCard cardToAdd = null;
        switch(chaosRoll) {
            case 1:
                cardToAdd = new ChaosOne();  
                break;
            case 2:
                cardToAdd = new ChaosTwo();  
                break;
            case 3:
                cardToAdd = new ChaosThree();  
                break;
            case 4:
                cardToAdd = new ChaosFour();  
                break;
            case 5:
                cardToAdd = new ChaosFive();  
                break;
            case 6:
                cardToAdd = new ChaosSix();  
                break;
        }
        return cardToAdd;
    }

    public void update() {
        for (int i = 0; i < this.amount; i++) {
            addToBot(new MakeTempCardInHandAction(roll(), 1));
        }
        this.isDone = true;
    }
}
