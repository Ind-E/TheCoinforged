package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;

public class ChippedBlade extends AbstractCoinforgedCard {
    private static final int MAGIC = 12;
    private static final int UPG_MAGIC = 3;
    private static final int WEAK = 2;
    private static final int UPG_WEAK = 1;

    public static final String ID = makeID(ChippedBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2);

    public ChippedBlade() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("W", WEAK, UPG_WEAK);
    }

    // public ChippedBlade(ChipColor chip) {
    //     super(ID, info, chip);
    //     setDamage(DAMAGE, UPG_DAMAGE);
    //     setMagic(MAGIC, UPG_MAGIC);
    // }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new CripplingDebtPower(m, this.magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, customVar("W"), false)));
    }

    @Override
    public AbstractCard makeCopy() {
        // return this.chip == null ? new ChippedBlade() : new ChippedBlade(this.chip);
        return new ChippedBlade();
    }
}