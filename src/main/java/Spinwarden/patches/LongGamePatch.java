package Spinwarden.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Spinwarden.cards.LongGame;
import Spinwarden.powers.LongGamePower;
import javassist.CtBehavior;

@SpirePatch(clz = CardGroup.class, method = "initializeDeck")
public class LongGamePatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(CardGroup __instance) {
        if (__instance.size() > 0 && __instance.getTopCard() instanceof LongGame) {
            LongGame card = (LongGame) __instance.getTopCard();
            System.out.println("LongGamePatch Upgraded: " + card.upgraded);
            AbstractPlayer p = AbstractDungeon.player;
            __instance.removeTopCard();
            AbstractDungeon.actionManager
                    .addToBottom(
                            new ApplyPowerAction(p, p, new LongGamePower(p, card.magicNumber, card)));
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "addToTop");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
