package Spinwarden.relics;

import static Spinwarden.SpinwardenMain.makeID;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import Spinwarden.SpinwardenMain;
import Spinwarden.actions.CasinoCatalystAction;
import Spinwarden.character.SpinwardenCharacter;

public class CasinoCatalyst extends BaseRelic {
    private static final String NAME = "CasinoCatalyst";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.HEAVY;
    private boolean markedCardPlayed = false;

    public CasinoCatalyst() {
        super(ID, NAME, SpinwardenCharacter.Enums.CARD_COLOR, TIER, sfx);
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
        if (!markedCardPlayed && AbstractDungeon.player.hasRelic(LuckRelic.ID)) {
            LuckRelic rabbitsFoot = (LuckRelic) AbstractDungeon.player.getRelic(LuckRelic.ID);
            if (rabbitsFoot != null) {
                rabbitsFoot.flash();
                rabbitsFoot.setChance(rabbitsFoot.getChance() + 1);
            }
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (markedCardPlayed == false && c.hasTag(SpinwardenMain.MARKED)) {
            markedCardPlayed = true;
            this.pulse = false;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CasinoCatalyst();
    }

}
