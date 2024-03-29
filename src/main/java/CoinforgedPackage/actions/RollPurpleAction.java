package CoinforgedPackage.actions;

import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import CoinforgedPackage.cards.tempCards.PurpleFive;
import CoinforgedPackage.cards.tempCards.PurpleFour;
import CoinforgedPackage.cards.tempCards.PurpleOne;
import CoinforgedPackage.cards.tempCards.PurpleSix;
import CoinforgedPackage.cards.tempCards.PurpleThree;
import CoinforgedPackage.cards.tempCards.PurpleTwo;

public class RollPurpleAction extends RollBaseAction {

    public RollPurpleAction(AbstractCreature owner, int magic) {
        super(owner, magic);
    }

    public RollPurpleAction(AbstractCreature owner, int magic, int minroll, int maxroll) {
        super(owner, magic, minroll, maxroll);
    }

    @Override
    public AbstractCard roll() {
        int block = ThreadLocalRandom.current().nextInt(minroll, maxroll + 1);

        AbstractCard cardToAdd = null;
        switch (block) {
            case 1:
                cardToAdd = new PurpleOne();
                break;
            case 2:
                cardToAdd = new PurpleTwo();
                break;
            case 3:
                cardToAdd = new PurpleThree();
                break;
            case 4:
                cardToAdd = new PurpleFour();
                break;
            case 5:
                cardToAdd = new PurpleFive();
                break;
            case 6:
                cardToAdd = new PurpleSix();
                break;
            default:
                cardToAdd = new PurpleSix(block);
                break;
        }
        return cardToAdd;
    }
}
