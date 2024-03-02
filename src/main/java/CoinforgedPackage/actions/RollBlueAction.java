package CoinforgedPackage.actions;

import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.tempCards.BlueFive;
import CoinforgedPackage.cards.tempCards.BlueFour;
import CoinforgedPackage.cards.tempCards.BlueOne;
import CoinforgedPackage.cards.tempCards.BlueSix;
import CoinforgedPackage.cards.tempCards.BlueThree;
import CoinforgedPackage.cards.tempCards.BlueTwo;

public class RollBlueAction extends RollBaseAction {

    public RollBlueAction(AbstractCreature owner, int magic) {
        super(owner, magic);
    }

    public RollBlueAction(AbstractCreature owner, int magic, int minroll, int maxroll) {
        super(owner, magic, minroll, maxroll);
    }

    @Override
    public AbstractCard roll() {
        int block = ThreadLocalRandom.current().nextInt(minroll, maxroll + 1);

        AbstractCard cardToAdd = null;
        switch (block) {
            case 1:
                cardToAdd = new BlueOne();
                break;
            case 2:
                cardToAdd = new BlueTwo();
                break;
            case 3:
                cardToAdd = new BlueThree();
                break;
            case 4:
                cardToAdd = new BlueFour();
                break;
            case 5:
                cardToAdd = new BlueFive();
                break;
            case 6:
                cardToAdd = new BlueSix();
                break;
            default:
                cardToAdd = new BlueSix(block);
                break;
        }
        return cardToAdd;
    }
}
