package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;

public class BlackChip extends BaseChip {

    public static final String ID = makeID(BlackChip.class.getSimpleName());
    public static final int BlackChipValue = 3;

    public BlackChip() {
        super(ID, info);
        setMagic(BlackChipValue);
        this.chipColor = ChipColor.Black;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}