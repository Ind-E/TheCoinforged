package GamblerMod.relics;

import static GamblerMod.GamblerMod.makeID;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import GamblerMod.character.Gambler;
import GamblerMod.GamblerMod;
import GamblerMod.actions.CasinoCatalystAction;

public class CasinoCatalyst extends BaseRelic {
    private static final String NAME = "CasinoCatalyst";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.HEAVY;
    private boolean markedCardPlayed = false;

    public CasinoCatalyst() {
        super(ID, NAME, Gambler.Enums.CARD_COLOR, TIER, sfx);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atPreBattle() {
        flash();
        if (!this.pulse) {
            beginPulse();
            this.pulse = true;
        }
    }

    @Override
    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new CasinoCatalystAction());
    }

    @Override
    public void onVictory() {
        this.pulse = false;
        if (!markedCardPlayed && AbstractDungeon.player.hasRelic(RabbitsFoot.ID)) {
            RabbitsFoot rabbitsFoot = (RabbitsFoot) AbstractDungeon.player.getRelic(RabbitsFoot.ID);
            if (rabbitsFoot != null) {
                rabbitsFoot.flash();
                rabbitsFoot.setChance(rabbitsFoot.getChance() + 1);
            }
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (markedCardPlayed == false && c.hasTag(GamblerMod.MARKED)) {
            markedCardPlayed = true;
            this.pulse = false;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CasinoCatalyst();
    }

}
