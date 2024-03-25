package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;

public class GreenChip extends BaseChip {

    public static final String ID = makeID(GreenChip.class.getSimpleName());

    public GreenChip() {
        super(ID, info);
        setSelfRetain(true);
        this.chipColor = ChipColor.Green;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}