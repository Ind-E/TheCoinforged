package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;

public class Cripple extends BaseCard{
    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public static final String ID = makeID(Cripple.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        1
    );

    public Cripple() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new CripplingDebtPower(m, this.magicNumber)));
    }
}