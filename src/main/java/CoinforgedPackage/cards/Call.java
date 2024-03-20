package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.CallAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class Call extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static final int ENERGY = 2;
    public static final int UPG_ENERGY = 1;

    public static final String ID = makeID(Call.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public Call() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.baseDiscard = this.discard = ENERGY;
    }

    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.baseDiscard += UPG_ENERGY;
            this.discard = this.baseDiscard;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(p, p, 1, false));
        addToBot(new CallAction(this, this.damage, this.magicNumber, this.discard));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Call();
    }
}
