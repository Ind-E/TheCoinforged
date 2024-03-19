package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WhiteChip extends BaseChip {
    {
        ID = makeID(WhiteChip.class.getSimpleName());
    }

    public WhiteChip() {
        super();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}