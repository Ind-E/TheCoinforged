package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class Jackpot extends AbstractCoinforgedCard {
    private static final int CARD_DRAW = 5;
    private static final int UPG_CARD_DRAW = 5;
    public static final String ID = makeID(Jackpot.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0);

    public Jackpot() {
        super(ID, info);
        setMagic(CARD_DRAW, UPG_CARD_DRAW);
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.group.size() <= 1) {
            addToBot(new DrawCardAction(p, this.magicNumber));
            addToBot(new GainEnergyAction(2));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Jackpot();
    }
}
