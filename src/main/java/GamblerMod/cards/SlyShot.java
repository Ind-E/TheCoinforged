package GamblerMod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.DamageLowestHealthEnemyAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class SlyShot extends BaseCard{
    private static final int DAMAGE = 5;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(SlyShot.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ALL_ENEMY,
        1
    );

    public SlyShot() {
        super(ID, info);
        setDamage(DAMAGE); 
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageLowestHealthEnemyAction(this.damage));
        }
    }
}