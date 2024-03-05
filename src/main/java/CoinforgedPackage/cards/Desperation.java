package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.DesperationAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

//TODO: rework or remove
public class Desperation extends BaseCard {
    private static final int CARD_DRAW = 4;

    public static final String ID = makeID(Desperation.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0);

    public Desperation() {
        super(ID, info);
        setMagic(CARD_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new DesperationAction(this.upgraded));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Desperation();
    }
}
