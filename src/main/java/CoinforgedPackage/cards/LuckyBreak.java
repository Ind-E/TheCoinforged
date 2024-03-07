package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.LuckyBreakFollowUpAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class LuckyBreak extends BaseCard {
    private static final int DEXTERITY = 1;
    private static final int UPG_DEXTERITY = 1;
    private static final int CARD_DRAW = 2;

    public static final String ID = makeID(LuckyBreak.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public LuckyBreak() {
        super(ID, info);
        setMagic(DEXTERITY, UPG_DEXTERITY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(CARD_DRAW, new LuckyBreakFollowUpAction(this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LuckyBreak();
    }
}
