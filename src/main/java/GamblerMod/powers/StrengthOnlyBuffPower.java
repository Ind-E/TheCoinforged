package GamblerMod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class StrengthOnlyBuffPower extends StrengthPower{

    public StrengthOnlyBuffPower(AbstractCreature owner, int amount) {
        super(owner, amount);
    }

    public void updateDescription() {
        if (this.amount > 0) {
           this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
           this.type = PowerType.BUFF;
        } else {
           int tmp = -this.amount;
           this.description = DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2];
           this.type = PowerType.BUFF;
        }
  
     }
}