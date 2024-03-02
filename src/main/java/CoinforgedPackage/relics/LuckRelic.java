package CoinforgedPackage.relics;

import static CoinforgedPackage.CoinforgedMod.makeID;

import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import CoinforgedPackage.character.Coinforged;

public class LuckRelic extends BaseRelic {
    private static final int STARTING_COUNTER = 1;
    private static final String NAME = "LuckRelic";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.FLAT;

    public LuckRelic() {
        super(ID, NAME, Coinforged.Enums.CARD_COLOR, TIER, sfx);
        setCounter(STARTING_COUNTER);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + this.counter + this.DESCRIPTIONS[1];
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LuckRelic();
    }

}
