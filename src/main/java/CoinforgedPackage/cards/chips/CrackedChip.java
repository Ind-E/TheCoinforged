package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;

//code to exhaust on spend is in SpendChipsAction
public class CrackedChip extends BaseChip {

    public static final String ID = makeID(CrackedChip.class.getSimpleName());

    public CrackedChip() {
        super(ID, info);
        this.chipColor = ChipColor.Cracked;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}