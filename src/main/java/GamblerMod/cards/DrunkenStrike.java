package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class DrunkenStrike extends BaseCard {
    private static final int DAMAGE = 13;
    private static final int UPG_DAMAGE = 5;

    public static final String ID = makeID(DrunkenStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            -2);

    public DrunkenStrike() {
        super(ID, info);
        this.damageType = DamageInfo.DamageType.NORMAL;
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void triggerWhenDrawn() {
        this.cost = getRandomCost();
    }

    private int getRandomCost() {
        return AbstractDungeon.cardRandomRng.random(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageRandomEnemyAction(new DamageInfo(p, this.damage, this.damageType),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DrunkenStrike();
    }
}
