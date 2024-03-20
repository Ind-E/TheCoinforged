package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class StrikeCoinforged extends AbstractCoinforgedCard {
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(StrikeCoinforged.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1);

    public StrikeCoinforged() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_LIGHT));
    }

    public AbstractCard makeCopy() {
        return new StrikeCoinforged();
    }
}
