package CoinforgedPackage.cards;

import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

// import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class CursedDie extends AbstractCoinforgedCard {

    public static final String ID = makeID(CursedDie.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.CURSE,
            CardType.CURSE,
            CardRarity.CURSE,
            CardTarget.NONE,
            1);

    public CursedDie() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int num = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        if (num == 6) {
            AbstractDungeon.player.masterDeck.removeCard(CursedDie.ID);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CursedDie();
    }

}
