package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;
import basemod.BaseMod;
// TODO: add reminder power to make less confusing and more clear that minimum hand size is 1.
public class CoinBarrage extends BaseCard{
    private static final int DAMAGE = 1;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = -1;

    public static final String ID = makeID(CoinBarrage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.RARE, 
            CardTarget.ENEMY, 
            1 
    );

    public CoinBarrage() {
        super(ID, info);
        setDamage(DAMAGE); 
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 12; i++)
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));     
            for (int i = 0; i < this.magicNumber; i++)
                if (BaseMod.MAX_HAND_SIZE > 1)
                    BaseMod.MAX_HAND_SIZE--;
    }

}
