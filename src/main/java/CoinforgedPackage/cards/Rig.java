package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RigAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.RigPower;
import CoinforgedPackage.util.CardStats;

public class Rig extends AbstractCoinforgedCard {
    private static final int CARD_DRAW = 1;
    public static final String ID = makeID(Rig.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2);

    public Rig() {
        super(ID, info);
        setMagic(CARD_DRAW);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RigAction());
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new RigPower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Rig();
    }
}
