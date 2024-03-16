package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;

public class Orange extends BaseCard{
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int TIMES_TO_DEAL_DAMAGE = 2;

    public static final String ID = makeID(Orange.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        1
    );

    public Orange() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo dmgInfo = new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL);
        dmgInfo.name = "Coinforged_Orange";
        for (int i = 0; i < TIMES_TO_DEAL_DAMAGE; i++) {
            addToBot(new DamageAction(m, dmgInfo, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
}