package GamblerMod.relics;

import static GamblerMod.GamblerMod.makeID;

import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import GamblerMod.character.Gambler;

public class RabbitsFoot extends BaseRelic {
    private static final int STARTING_CHANCE = 10;
    private static final String NAME = "RabbitsFoot";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.UNCOMMON;
    private static final LandingSound sfx = LandingSound.FLAT;
    private int chance;

    public RabbitsFoot() {
        super(ID, NAME, Gambler.Enums.CARD_COLOR, TIER, sfx);
        setChance(STARTING_CHANCE);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + this.chance + this.DESCRIPTIONS[1];
    }

    public int getChance() {
        return this.chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RabbitsFoot();
    }

}
