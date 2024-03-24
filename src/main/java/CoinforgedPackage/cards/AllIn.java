package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.actions.SpendChipsAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class AllIn extends AbstractCoinforgedCard {

    public static final String ID = makeID(AllIn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1);

    public AllIn() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int chips = getNumChips();
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -chips - (upgraded ? 1 : 0))));
        addToBot(new SpendChipsAction(chips));
    }

    @Override
    public AbstractCard makeCopy() {
        return new AllIn();
    }

}
