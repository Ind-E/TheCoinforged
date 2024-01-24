package GamblerMod.cards.tempCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.GamblerMod;

import GamblerMod.cards.BaseCard;
import GamblerMod.util.CardStats;

public class RedDieBase extends BaseCard{
    private static final int DAMAGE = 99;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedDieBase.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS, 
            CardType.ATTACK, 
            CardRarity.SPECIAL, 
            CardTarget.ENEMY, 
            0 
    );

    public RedDieBase(String ID, int DAMAGE, int UPG_DAMAGE) {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.exhaust = true; 
        tags.add(GamblerMod.DIE);
    }

    public RedDieBase(String ID, CardStats info, int DAMAGE, int UPG_DAMAGE) {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.exhaust = true; 
        tags.add(GamblerMod.DIE);
    }

    public RedDieBase() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.exhaust = true; 
        tags.add(GamblerMod.DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

}
