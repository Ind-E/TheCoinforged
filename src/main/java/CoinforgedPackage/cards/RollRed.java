package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class RollRed extends AbstractCoinforgedCard {
    private static final int DICE_TO_ROLL = 2;
    private static final int UPG_DICE_TO_ROLL = 1;

    public float rotationTimer;
    public int previewIndex;
    public ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(RollRed.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            1);

    public RollRed() {
        super(ID, info, true);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollRedAction(p, this.magicNumber));
    }

    public ArrayList<CardTags> getTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new RollRed();
    }
}
