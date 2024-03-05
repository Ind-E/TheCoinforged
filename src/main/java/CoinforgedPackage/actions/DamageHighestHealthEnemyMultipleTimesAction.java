package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DamageHighestHealthEnemyMultipleTimesAction extends AbstractGameAction {
    private int damage;
    private int times;

    public DamageHighestHealthEnemyMultipleTimesAction(int damage, int times) {
        this.damage = damage;
        this.times = times;
    }

    @Override
    public void update() {
        for (int i = 0; i < this.times; i++) {
            addToTop(new DamageHighestHealthEnemyAction(this.damage));
        }
        this.isDone = true;
    }
}