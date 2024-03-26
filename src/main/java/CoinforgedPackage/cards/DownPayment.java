package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;

public class DownPayment extends AbstractCoinforgedCard{
    private static final int MAGIC = 15;
    private static final int UPG_MAGIC = 5;

    public static final String ID = makeID(DownPayment.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        1
    );

    public DownPayment() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setInnate(true);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new CripplingDebtPower(m, this.magicNumber), this.magicNumber));
    }
}