package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.cards.chips.WhiteChip;
import CoinforgedPackage.character.Coinforged;

public class Stash extends AbstractRandomChipCard{
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public static final String ID = makeID(Stash.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        1
    );

    public Stash() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        this.cardsToPreview = new WhiteChip();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
    }
}