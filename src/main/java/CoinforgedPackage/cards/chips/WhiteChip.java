package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WhiteChip extends BaseChip {

    public static final String ID = makeID(WhiteChip.class.getSimpleName());

    public WhiteChip() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}