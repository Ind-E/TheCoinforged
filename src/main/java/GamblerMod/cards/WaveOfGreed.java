package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class WaveOfGreed extends BaseCard {
    private static final int DAMAGE = 35;
    private static final int UPG_DAMAGE = 10;

    public static final String ID = makeID(ShuffleStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.RARE,
        CardTarget.ALL_ENEMY,
        3
    );

    public WaveOfGreed() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        int numEnemiesDamaged = AbstractDungeon.getMonsters().monsters.stream().filter(monster -> !monster.isDeadOrEscaped() && !monster.halfDead).toArray().length;
        addToBot(new DiscardAction(p, p, numEnemiesDamaged, false));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new WaveOfGreed();
    }

}
