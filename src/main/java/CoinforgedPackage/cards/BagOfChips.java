package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.BagOfChipsAction;
import CoinforgedPackage.cards.chips.WhiteChip;
import CoinforgedPackage.character.Coinforged;

public class BagOfChips extends AbstractMultiPreviewCard{

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
        this.cardsToPreview = new WhiteChip();
        setCostUpgrade(0);
        this.timerDuration = 1.75f;
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BagOfChipsAction(p));
    }

    @Override
    public ArrayList<CardTags> getTags() {
        ArrayList<CardTags> tags = new ArrayList<CardTags>();
        tags.add(CustomTags.POKER_CHIP);
        return tags;
    }
}