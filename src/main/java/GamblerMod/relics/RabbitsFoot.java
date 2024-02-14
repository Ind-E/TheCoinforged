package GamblerMod.relics;

import static GamblerMod.GamblerMod.makeID;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import GamblerMod.character.Gambler;

public class RabbitsFoot extends BaseRelic {
    private static final int STARTING_CHANCE = 50;
    private static final String NAME = "RabbitsFoot";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.SOLID;
    private int chance;

    {
        setChance(STARTING_CHANCE);
    }

    

    public RabbitsFoot() {
        super(ID, NAME, Gambler.Enums.CARD_COLOR, TIER, sfx);
        setChance(STARTING_CHANCE);
        this.description = getUpdatedDescription();
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
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RabbitsFoot();
    }
}
