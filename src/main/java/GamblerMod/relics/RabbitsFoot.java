package GamblerMod.relics;

import static GamblerMod.GamblerMod.makeID;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.PrayerWheel;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

// Grants a small chance to double the rewards (gold, cards, relics) earned after each battle.
public class RabbitsFoot extends BaseRelic {
    public static final String ID = makeID(RabbitsFoot.class.getSimpleName());
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound sfx = LandingSound.SOLID;
    private float chance = 1f;

    public RabbitsFoot() {
        super(ID, TIER, sfx);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + chance * 100 + this.DESCRIPTIONS[1];
    }

    @Override
    public void onVictory() {
        if (AbstractDungeon.cardRandomRng.randomBoolean(chance)) {
            AbstractRoom cur = AbstractDungeon.getCurrRoom();
            flash();
            for (RewardItem r : cur.rewards) {
                if (r.type == RewardItem.RewardType.GOLD) {
                    cur.addGoldToRewards(r.goldAmt);
                } else if (r.type == RewardItem.RewardType.CARD) {
                    cur.addCardToRewards();
                } else if (r.type == RewardItem.RewardType.RELIC) {
                    cur.addRelicToRewards(AbstractDungeon.returnRandomRelic(r.relic.tier));
                }
            }

            cur.addRelicToRewards(AbstractDungeon.returnRandomRelic(RelicTier.COMMON));
            cur.addCardToRewards();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PrayerWheel();
    }
}
