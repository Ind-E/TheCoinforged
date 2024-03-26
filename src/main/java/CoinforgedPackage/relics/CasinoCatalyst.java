package CoinforgedPackage.relics;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import CoinforgedPackage.character.Coinforged;

public class CasinoCatalyst extends BaseRelic implements ClickableRelic {
    private static final String NAME = "CasinoCatalyst";
    public static final String ID = makeID(NAME);
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.HEAVY;
    private boolean usedThisCombat = false;
    private boolean bossOrElite = false;

    public CasinoCatalyst() {
        super(ID, NAME, Coinforged.Enums.CARD_COLOR, TIER, sfx);
        this.counter = 3;
    }

    public CasinoCatalyst(int counter) {
        super(ID, NAME, Coinforged.Enums.CARD_COLOR, TIER, sfx);
        this.counter = counter;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().monsters == null || AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead() || AbstractDungeon.actionManager.turnHasEnded || AbstractDungeon.getCurrRoom().phase != RoomPhase.COMBAT) {
            return;
        }
        if (counter < 1) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                    AbstractDungeon.player.dialogY, 3.0F, this.DESCRIPTIONS[1], true));
            return;
        }
        addToBot(new GainEnergyAction(1));
        addToBot(new DrawCardAction(1));
        flash();
        setCounter(counter - 1);
        this.usedThisCombat = true;
    }

    @Override
    public void atBattleStart() {
        this.usedThisCombat = false;
        this.bossOrElite = AbstractDungeon.getMonsters().monsters.stream()
                .anyMatch(m -> m.type == EnemyType.BOSS || m.type == EnemyType.ELITE);
    }

    @Override
    public void onVictory() {
        if (!usedThisCombat && AbstractDungeon.player.hasRelic(LuckRelic.ID)) {
            LuckRelic luckRelic = (LuckRelic) AbstractDungeon.player.getRelic(LuckRelic.ID);
            if (luckRelic != null) {
                luckRelic.flash();
                luckRelic.setCounter(luckRelic.getCounter() + 1);
            }
        }
        if (bossOrElite) {
            setCounter(this.counter + 1);
            flash();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CasinoCatalyst(this.counter);
    }

}
