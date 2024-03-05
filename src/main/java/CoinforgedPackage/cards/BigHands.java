package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.BigHandsFollowUpAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class BigHands extends BaseCard {
    private static final int CARD_DRAW = 5;
    private static final int UPG_CARD_DRAW = 2;

    public static final String ID = makeID(BigHands.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0);

    public BigHands() {
        super(ID, info);
        setMagic(CARD_DRAW, UPG_CARD_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(this.magicNumber, new BigHandsFollowUpAction()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BigHands();
    }
}
