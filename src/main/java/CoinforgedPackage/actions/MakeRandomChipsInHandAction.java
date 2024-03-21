package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.chips.BlackChip;
import CoinforgedPackage.cards.chips.BlueChip;
import CoinforgedPackage.cards.chips.CrackedChip;
import CoinforgedPackage.cards.chips.GrayChip;
import CoinforgedPackage.cards.chips.GreenChip;
import CoinforgedPackage.cards.chips.OrangeChip;
import CoinforgedPackage.cards.chips.RedChip;
import CoinforgedPackage.cards.chips.WhiteChip;

public class MakeRandomChipsInHandAction extends AbstractGameAction {

    public MakeRandomChipsInHandAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        AbstractCard chip;
        for (int i = 0; i < amount; i++) {
            chip = getRandomChip();

            addToTop(new MakeTempCardInHandAction(chip));
        }

        isDone = true;
    }

    private static AbstractCard getRandomChip() {
        int roll = AbstractDungeon.cardRandomRng.random(7);
        switch (roll) {
            case 0:
                return new BlueChip();
            case 1:
                return new BlackChip();
            case 2:
                return new CrackedChip();
            case 3:
                return new GrayChip();
            case 4:
                return new GreenChip();
            case 5:
                return new OrangeChip();
            case 6:
                return new RedChip();
            case 7:
                return new WhiteChip();
            default:
                return null;
        }
    }

}
