package GamblerMod.patches;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;

import GamblerMod.relics.RabbitsFoot;
import javassist.CtBehavior;

public class RabbitsFootPatch {
    @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
    public static class CombatRewardScreenPatches_SetupItemReward {

        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(CombatRewardScreen __instance) {
            if (AbstractDungeon.player.hasRelic(RabbitsFoot.ID)) {
                RabbitsFoot rabbitsFoot = (RabbitsFoot) AbstractDungeon.player.getRelic(RabbitsFoot.ID);
                if (rabbitsFoot != null
                        && AbstractDungeon.cardRandomRng.randomBoolean(rabbitsFoot.getChance() / 100f)) {
                    ArrayList<RewardItem> toAdd = new ArrayList<>();
                    for (RewardItem item : __instance.rewards) {
                        if (item.type == RewardItem.RewardType.GOLD) {
                            toAdd.add(new RewardItem(item.goldAmt));
                        } else if (item.type == RewardItem.RewardType.CARD) {
                            toAdd.add(new RewardItem());
                        } else if (item.type == RewardItem.RewardType.RELIC) {
                            toAdd.add(new RewardItem(AbstractDungeon.returnRandomRelic(item.relic.tier)));
                        } else if (item.type == RewardItem.RewardType.POTION) {
                            toAdd.add(new RewardItem(AbstractDungeon.returnRandomPotion()));
                        }
                    }
                    __instance.rewards.addAll(toAdd);
                    rabbitsFoot.flash();

                }
            }

        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(CombatRewardScreen.class, "positionRewards");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
