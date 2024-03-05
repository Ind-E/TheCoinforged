package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.BrokenBottleAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

import com.megacrit.cardcrawl.cards.DamageInfo;

//TODO: seems weak
public class BrokenBottle extends BaseCard {
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    private static final int X_COST = -1;

    public static final String ID = makeID(BrokenBottle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            X_COST);

    public BrokenBottle() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.damageType = this.damageTypeForTurn = DamageInfo.DamageType.NORMAL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BrokenBottleAction(m, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }
}