package CoinforgedPackage.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.NoDrawPower;

public class NoDrawBuffPower extends NoDrawPower{

    public NoDrawBuffPower(AbstractCreature owner) {
        super(owner);
        this.type = PowerType.BUFF;
    }
    
}
