package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class RedChip extends BaseChip {

    public static final String ID = makeID(RedChip.class.getSimpleName());

    private static final int MAGIC = 1;

    public RedChip() {
        super(ID, info);
        this.cost = costForTurn = 0;
        setExhaust(true);
        this.canUse = true;
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
    }
}