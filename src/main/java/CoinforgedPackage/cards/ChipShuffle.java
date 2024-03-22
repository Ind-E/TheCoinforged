package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.ChipShuffleAction;
import CoinforgedPackage.character.Coinforged;

//TODO: rethink
public class ChipShuffle extends AbstractCoinforgedCard {
    public static final int BLOCK = 2;

    public static final String ID = makeID(ChipShuffle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public ChipShuffle() {
        super(ID, info);
        setBlock(BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new GainBlockAction(p, getNumChips()));
        }
        addToBot(new ChipShuffleAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChipShuffle();
    }
}