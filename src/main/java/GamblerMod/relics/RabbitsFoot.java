package GamblerMod.relics;

import static GamblerMod.GamblerMod.makeID;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import GamblerMod.character.Gambler;

public class RabbitsFoot extends BaseRelic {
    private static final String NAME = "RabbitsFoot";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.SOLID;

    public int chance = 99;

    public RabbitsFoot() {
        super(ID, NAME, Gambler.Enums.CARD_COLOR, TIER, sfx);
        getUpdatedDescription();
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + this.chance + this.DESCRIPTIONS[1];
    }

    public int getChance() {
        return this.chance;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RabbitsFoot();
    }
}
