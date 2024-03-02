package CoinforgedPackage.actions;

import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.tempCards.GreenFive;
import CoinforgedPackage.cards.tempCards.GreenFour;
import CoinforgedPackage.cards.tempCards.GreenOne;
import CoinforgedPackage.cards.tempCards.GreenSix;
import CoinforgedPackage.cards.tempCards.GreenThree;
import CoinforgedPackage.cards.tempCards.GreenTwo;

public class RollGreenAction extends RollBaseAction {

    public RollGreenAction(AbstractCreature owner, int magic) {
        super(owner, magic);
    }

    public RollGreenAction(AbstractCreature owner, int magic, int minroll, int maxroll) {
        super(owner, magic, minroll, maxroll);
    }

    @Override
    public AbstractCard roll() {
        int str_to_lose = ThreadLocalRandom.current().nextInt(minroll, maxroll + 1);

        AbstractCard cardToAdd = null;
        switch (str_to_lose) {
            case 1:
                cardToAdd = new GreenOne();
                break;
            case 2:
                cardToAdd = new GreenTwo();
                break;
            case 3:
                cardToAdd = new GreenThree();
                break;
            case 4:
                cardToAdd = new GreenFour();
                break;
            case 5:
                cardToAdd = new GreenFive();
                break;
            case 6:
                cardToAdd = new GreenSix();
                break;
            default:
                cardToAdd = new GreenSix(str_to_lose);
                break;
        }
        return cardToAdd;
    }
}
