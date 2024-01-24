package GamblerMod.cards.tempCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.GamblerMod;
import GamblerMod.cards.BaseCard;
import GamblerMod.util.CardStats;

public class RedOne extends BaseCard{
    private static final int DAMAGE = 1;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedOne.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS, 
            CardType.ATTACK, 
            CardRarity.SPECIAL, 
            CardTarget.ENEMY, 
            0 
    );

    public RedOne() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE);
        this.exhaust = true; 
        tags.add(GamblerMod.DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

}
