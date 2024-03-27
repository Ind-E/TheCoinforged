package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.character.Coinforged;

public class Bond extends AbstractCoinforgedCard{
    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 4;

    public static final String ID = makeID(Bond.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.SPECIAL,
        CardTarget.ENEMY,
        1
    );

    public Bond() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
        setSelfRetain(true);
    }

    public Bond(int damage) {
        super(ID, info);
        setDamage(damage, UPG_DAMAGE); 
        setSelfRetain(true);
    }

    public Bond(boolean preview) {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
        setSelfRetain(true);
        if (preview) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        
    }

    @Override
    public void onRetained() {
        this.upgradeDamage(this.damage / 2);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bond(this.damage);
    }
}