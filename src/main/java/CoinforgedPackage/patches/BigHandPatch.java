package CoinforgedPackage.patches;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import javassist.CtBehavior;

@SpirePatch(clz = CardGroup.class, method = "refreshHandLayout")
public class BigHandPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(CardGroup __instance) {
        switch (__instance.group.size()) {
            case 11: // this one works pretty well, adjust other cases to be similar
                for (int i = 2; i < 9; i++) {
                    (__instance.group.get(i)).target_y += 30.0F * Settings.scale;
                }
                (__instance.group.get(0)).target_y -= 60.0F * Settings.scale;
                (__instance.group.get(0)).targetAngle += 7.0F;
                (__instance.group.get(1)).target_y += 5.0F * Settings.scale;
                (__instance.group.get(1)).targetAngle += 2.5F;
                (__instance.group.get(2)).target_y += 7.5F * Settings.scale;
                (__instance.group.get(3)).target_y += 10.0F * Settings.scale;
                (__instance.group.get(7)).target_y += 10.0F * Settings.scale;
                (__instance.group.get(8)).target_y += 7.5F * Settings.scale;
                (__instance.group.get(9)).target_y += 5.0F * Settings.scale;
                (__instance.group.get(9)).targetAngle -= 2.5F;
                (__instance.group.get(10)).target_y -= 60.0F * Settings.scale;
                (__instance.group.get(10)).targetAngle -= 7.0F;
                for (AbstractCard c : __instance.group)
                    c.targetDrawScale = 0.628125F;
                break;
            case 12:
                (__instance.group.get(1)).target_y += 22.0F * Settings.scale;
                (__instance.group.get(2)).target_y += 18.0F * Settings.scale;
                (__instance.group.get(3)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(4)).target_y += 10.0F * Settings.scale;
                (__instance.group.get(5)).target_y += 4.0F * Settings.scale;
                (__instance.group.get(6)).target_y += 4.0F * Settings.scale;
                (__instance.group.get(7)).target_y += 10.0F * Settings.scale;
                (__instance.group.get(8)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(9)).target_y += 18.0F * Settings.scale;
                (__instance.group.get(10)).target_y += 22.0F * Settings.scale;
                for (AbstractCard c : __instance.group)
                    c.targetDrawScale = 0.61875F;
                break;
            case 13:
                (__instance.group.get(1)).target_y += 23.0F * Settings.scale;
                (__instance.group.get(2)).target_y += 20.5F * Settings.scale;
                (__instance.group.get(3)).target_y += 16.5F * Settings.scale;
                (__instance.group.get(4)).target_y += 9.5F * Settings.scale;
                (__instance.group.get(5)).target_y += 4.0F * Settings.scale;
                (__instance.group.get(6)).target_y += 0.0F * Settings.scale;
                (__instance.group.get(7)).target_y += 4.0F * Settings.scale;
                (__instance.group.get(8)).target_y += 9.5F * Settings.scale;
                (__instance.group.get(9)).target_y += 16.5F * Settings.scale;
                (__instance.group.get(10)).target_y += 20.5F * Settings.scale;
                (__instance.group.get(11)).target_y += 23.0F * Settings.scale;
                for (AbstractCard c : __instance.group)
                    c.targetDrawScale = 0.609375F;
                break;
            case 14:
                (__instance.group.get(1)).target_y += 19.0F * Settings.scale;
                (__instance.group.get(2)).target_y += 17.0F * Settings.scale;
                (__instance.group.get(3)).target_y += 16.0F * Settings.scale;
                (__instance.group.get(4)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(5)).target_y += 10.0F * Settings.scale;
                (__instance.group.get(6)).target_y += 4.0F * Settings.scale;
                (__instance.group.get(7)).target_y += 4.0F * Settings.scale;
                (__instance.group.get(8)).target_y += 10.0F * Settings.scale;
                (__instance.group.get(9)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(10)).target_y += 16.0F * Settings.scale;
                (__instance.group.get(11)).target_y += 17.0F * Settings.scale;
                (__instance.group.get(12)).target_y += 19.0F * Settings.scale;
                for (AbstractCard c : __instance.group)
                    c.targetDrawScale = 0.6F;
                break;
            case 15:
                (__instance.group.get(0)).target_y -= 50.0F * Settings.scale;
                (__instance.group.get(0)).targetAngle += 10.0F;
                (__instance.group.get(1)).target_y -= 25.0F * Settings.scale;
                (__instance.group.get(1)).targetAngle += 10.0F;
                (__instance.group.get(2)).target_y -= 10.0F * Settings.scale;
                (__instance.group.get(3)).targetAngle += 2.0F;
                (__instance.group.get(3)).target_y -= 5.0F * Settings.scale;
                (__instance.group.get(4)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(5)).target_y += 20.0F * Settings.scale;
                (__instance.group.get(6)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(7)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(8)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(9)).target_y += 20.0F * Settings.scale;
                (__instance.group.get(10)).target_y += 15.0F * Settings.scale;
                (__instance.group.get(11)).target_y -= 5.0F * Settings.scale;
                (__instance.group.get(12)).target_y -= 10.0F * Settings.scale;
                (__instance.group.get(12)).targetAngle -= 2.0F;
                (__instance.group.get(13)).target_y -= 25.0F * Settings.scale;
                (__instance.group.get(13)).targetAngle -= 10.0F;
                (__instance.group.get(14)).target_y -= 50.0F * Settings.scale;
                (__instance.group.get(14)).targetAngle -= 10.0F;
                for (AbstractCard c : __instance.group)
                    c.targetDrawScale = 0.590625F;
                break;
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "hoveredCard");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
