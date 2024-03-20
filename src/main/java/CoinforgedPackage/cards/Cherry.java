package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;

public class Cherry extends AbstractCoinforgedCard {
    private static final int DAMAGE = 0;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(Cherry.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2);

    public Cherry() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int damageAmount = p.hand.size() * this.magicNumber + this.damage;
        addToBot(new DamageAction(m, new DamageInfo(p, damageAmount, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int damageAmount = AbstractDungeon.player.hand.size() * this.magicNumber + this.damage;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0]
                + damageAmount + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cherry();
    }
}