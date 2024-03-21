package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.BuyInAction;
import CoinforgedPackage.cards.chips.GreenChip;
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
        this.cardsToPreview = new GreenChip();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new GreenChip(), this.magicNumber));
        addToBot(new BuyInAction());
    }
}