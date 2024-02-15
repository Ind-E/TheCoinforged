package Spinwarden.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RabbitsFootAction extends AbstractGameAction {
    private AbstractRoom cur;

    public RabbitsFootAction(AbstractRoom cur) {
        super();
        this.cur = cur;
    }

    public void update() {
        for (RewardItem r : cur.rewards) {
            if (r.type == RewardItem.RewardType.GOLD && r.goldAmt > 0) {
                cur.addGoldToRewards(r.goldAmt);
            } else if (r.type == RewardItem.RewardType.CARD && r.cards.size() > 0) {
                cur.addCardToRewards();
            } else if (r.type == RewardItem.RewardType.RELIC && r.relic != null) {
                cur.addRelicToRewards(AbstractDungeon.returnRandomRelic(r.relic.tier));
            }
        }
        this.isDone = true;
    }
}