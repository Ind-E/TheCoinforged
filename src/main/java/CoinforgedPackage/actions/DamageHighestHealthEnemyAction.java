package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageHighestHealthEnemyAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    private int damage;

    public DamageHighestHealthEnemyAction(int damage) {
        super();
        this.damage = damage;
    }

    public void update() {
        AbstractCreature target = AbstractDungeon.getMonsters().getRandomMonster(null, true,
                AbstractDungeon.cardRandomRng);
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (mo.currentHealth > 0 && mo.currentHealth > target.currentHealth && target.currentHealth > 0) {
                target = mo;
            }
        }
        addToTop(new DamageAction(target, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.NONE));
        this.isDone = true;
    }
}