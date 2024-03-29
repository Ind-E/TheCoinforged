package CoinforgedPackage.patches;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;

import CoinforgedPackage.relics.LuckRelic;

import com.megacrit.cardcrawl.relics.AbstractRelic;

import javassist.CtBehavior;

public class LuckRelicPatch {
    @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
    public static class CombatRewardScreenPatches_SetupItemReward {

        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(CombatRewardScreen __instance) {
            if (AbstractDungeon.player.hasRelic(LuckRelic.ID)) {
                LuckRelic rabbitsFoot = (LuckRelic) AbstractDungeon.player.getRelic(LuckRelic.ID);
                if (rabbitsFoot != null
                        && AbstractDungeon.cardRandomRng.randomBoolean(rabbitsFoot.getCounter() / 100f)) {
                    ArrayList<RewardItem> toAdd = new ArrayList<>();
                    for (RewardItem item : __instance.rewards) {
                        if (item.type == RewardItem.RewardType.GOLD) {
                            toAdd.add(new RewardItem(item.goldAmt));
                        } else if (item.type == RewardItem.RewardType.CARD) {
                            toAdd.add(new RewardItem());
                        } else if (item.type == RewardItem.RewardType.RELIC) {
                            if (item.relic.tier == AbstractRelic.RelicTier.SPECIAL) {
                                toAdd.add(new RewardItem(AbstractDungeon.returnRandomRelic(
                                        AbstractRelic.RelicTier.COMMON)));
                            } else {
                                toAdd.add(new RewardItem(AbstractDungeon.returnRandomRelic(item.relic.tier)));
                            }
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
