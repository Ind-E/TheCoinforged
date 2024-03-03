package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.IfChipsAction;
import CoinforgedPackage.actions.SpendChipsAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class AllIn extends BaseCard {
    private static final int DAMAGE = 100;
    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = -2;

    public static final String ID = makeID(AllIn.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            0);

    public AllIn() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int chips = getNumChips();
        if (chips >= this.magicNumber) {
            addToBot(new IfChipsAction(this.magicNumber,
                    new DamageAllEnemiesAction(p, multiDamage, DamageType.NORMAL, AttackEffect.SLASH_HORIZONTAL)));
        } else {
            addToBot(new SpendChipsAction(chips, true));
            setMagic(this.magicNumber - chips);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AllIn();
    }

}
