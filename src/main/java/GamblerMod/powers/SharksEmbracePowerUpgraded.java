package GamblerMod.powers;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SharksEmbracePowerUpgraded extends SharksEmbracePower {

    public SharksEmbracePowerUpgraded(AbstractCreature owner, int amount) {
        super(owner, amount);
    }

    public void onExhaust() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();

            addToBot(new DrawCardAction(this.owner, this.amount));
            addToBot(new DiscardAction(this.owner, this.owner, this.amount, false));

        }
    }
}
