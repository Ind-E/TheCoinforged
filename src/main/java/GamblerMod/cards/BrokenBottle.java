package GamblerMod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.BrokenBottleAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class BrokenBottle extends BaseCard {
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;
    private static final int X_COST = -1;

    public static final String ID = makeID(BrokenBottle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
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