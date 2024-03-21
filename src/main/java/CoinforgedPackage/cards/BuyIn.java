package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.BuyInAction;
import CoinforgedPackage.actions.MakeChipsInHandAction;
import CoinforgedPackage.character.Coinforged;

public class BuyIn extends AbstractCoinforgedCard {
    private static final int CHIPS = 2;

    public static final String ID = makeID(BuyIn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public BuyIn() {
        super(ID, info);
        setMagic(CHIPS);
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeChipsInHandAction(this.magicNumber));
        addToBot(new BuyInAction());
    }
}