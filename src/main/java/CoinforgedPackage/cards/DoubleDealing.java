package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.DoubleDealingAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class DoubleDealing extends AbstractCoinforgedCard {

    public static final String ID = makeID(DoubleDealing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public DoubleDealing() {
        super(ID, info);
        this.exhaust = true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoubleDealingAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new DoubleDealing();
    }
}
