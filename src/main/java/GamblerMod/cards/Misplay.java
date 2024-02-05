package GamblerMod.cards;
import java.util.concurrent.ThreadLocalRandom;

import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;


public class Misplay extends BaseCard{
    private static final int DAMAGE = 30;
    private static final int UPG_DAMAGE = 7;

    public static final String ID = makeID(Misplay.class.getSimpleName());

    private static final CardStats info = new CardStats(
    Gambler.Enums.CARD_COLOR, 
    CardType.ATTACK,
    CardRarity.COMMON,
    SelfOrEnemyTargeting.SELF_OR_ENEMY,
    2
    );

    public Misplay() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = SelfOrEnemyTargeting.getTarget(this);

        if (target == null)
            target = p;

        addToBot(new DamageAction(target, new DamageInfo(p, 2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        if (target == p) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        } else {
            int numMonsters = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            int rand = ThreadLocalRandom.current().nextInt(0, numMonsters + 1);

            if (numMonsters == 1 || rand == 0) {
                addToBot(new DamageAction(p, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE)); 
            } else {
                AbstractCreature r = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                while (r != target && numMonsters > 1) {
                    r = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                }
                addToBot(new DamageAction(r, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            }
            

        }
    }
}