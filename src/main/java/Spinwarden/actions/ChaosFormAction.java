package Spinwarden.actions;

import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import Spinwarden.cards.tempCards.ChaosFive;
import Spinwarden.cards.tempCards.ChaosFour;
import Spinwarden.cards.tempCards.ChaosOne;
import Spinwarden.cards.tempCards.ChaosSix;
import Spinwarden.cards.tempCards.ChaosThree;
import Spinwarden.cards.tempCards.ChaosTwo;

public class ChaosFormAction extends RollBaseAction {

    public ChaosFormAction(AbstractCreature owner) {
        super(owner, 1);
    }

    public AbstractCard roll() {
        int chaosRoll = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        AbstractCard cardToAdd = null;
        switch (chaosRoll) {
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
}
