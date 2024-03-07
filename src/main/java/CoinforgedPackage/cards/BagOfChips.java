package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.BagOfChipsAction;
import CoinforgedPackage.cards.tempCards.PokerChip;
import CoinforgedPackage.character.Coinforged;

public class BagOfChips extends BaseCard{

    public static final String ID = makeID(BagOfChips.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.NONE,
        1
    );

    public BagOfChips() {
        super(ID, info);
        this.cardsToPreview = new PokerChip();
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BagOfChipsAction(p));
    }
}