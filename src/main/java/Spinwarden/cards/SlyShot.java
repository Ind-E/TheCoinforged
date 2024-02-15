package Spinwarden.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.actions.DamageLowestHealthEnemyAction;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class SlyShot extends BaseCard {
    private static final int DAMAGE = 5;
    private static final int TIMES_TO_DEAL_DAMAGE = 2;
    private static final int UPG_TIMES_TO_DEAL_DAMAGE = 1;

    public static final String ID = makeID(SlyShot.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1);

    public SlyShot() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(TIMES_TO_DEAL_DAMAGE, UPG_TIMES_TO_DEAL_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageLowestHealthEnemyAction(this.damage));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SlyShot();
    }
}