package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;

//code for energy on spend in SpendChipsAction
public class GrayChip extends BaseChip {

    public static final String ID = makeID(GrayChip.class.getSimpleName());

    public GrayChip() {
        super(ID, info);
        this.chipColor = ChipColor.Gray;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}