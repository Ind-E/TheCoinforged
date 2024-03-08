package CoinforgedPackage.patches;

import java.util.Iterator;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;

import basemod.ReflectionHacks;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

public class NoCompendiumColorlessPatch {
    @SpirePatch(clz = CardLibraryScreen.class, method = "initialize")
    public static class Initialize {

        @SpirePostfixPatch()
        public static void Postfix(CardLibraryScreen __instance) {
            CardGroup colorlessCards = (CardGroup) ReflectionHacks.getPrivate(__instance, CardLibraryScreen.class,
                    "colorlessCards");

            Iterator<AbstractCard> iterator = colorlessCards.group.iterator();
            while (iterator.hasNext()) {
                AbstractCard card = iterator.next();
                NoCompendium noCompendium = card.getClass().getAnnotation(NoCompendium.class);
                if (noCompendium != null) {
                    iterator.remove();
                }
            }
        }
    }
}