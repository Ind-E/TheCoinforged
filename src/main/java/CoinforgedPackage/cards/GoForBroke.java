package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.GoForBrokePower;

public class GoForBroke extends AbstractCoinforgedCard{
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 0;

    public static final String ID = makeID(GoForBroke.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    public GoForBroke() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GoForBrokePower(p, 1)));
    }
}