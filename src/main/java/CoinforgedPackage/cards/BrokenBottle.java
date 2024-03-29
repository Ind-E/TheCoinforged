package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.BrokenBottleAction;
import CoinforgedPackage.cards.tempCards.GlassShard;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class BrokenBottle extends AbstractCoinforgedCard {
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int X_COST = -1;
    private static final int GLASS_SHARD = 1;

    public static final String ID = makeID(BrokenBottle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            X_COST);

    public BrokenBottle() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(GLASS_SHARD);
        this.damageType = this.damageTypeForTurn = DamageInfo.DamageType.NORMAL;
        this.cardsToPreview = new GlassShard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BrokenBottleAction(m, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
        addToBot(new MakeTempCardInHandAction(new GlassShard(), this.magicNumber));
    }
}