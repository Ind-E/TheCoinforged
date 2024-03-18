package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.LuckOfTheDrawPower;
import CoinforgedPackage.util.CardStats;

public class LuckOfTheDraw extends BaseCard {
    private static final int MAGIC = 1;

    public static final String ID = makeID(LuckOfTheDraw.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1);

    public LuckOfTheDraw() {
        super(ID, info);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LuckOfTheDrawPower(p, this.magicNumber)));
        if (upgraded) {
            addToBot(new DrawCardAction(p, 3));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LuckOfTheDraw();
    }
}
