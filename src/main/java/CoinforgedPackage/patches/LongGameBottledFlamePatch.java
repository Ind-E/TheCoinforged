package CoinforgedPackage.patches;

import java.util.Iterator;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import CoinforgedPackage.cards.LongGame;

@SpirePatch(clz = CardGroup.class, method = "getPurgeableCards")
public class LongGameBottledFlamePatch {

    @SpirePostfixPatch()
    public static CardGroup PostFix(CardGroup __retVal, CardGroup __instance) {
        Iterator<AbstractCard> iterator = __retVal.group.iterator();
        while (iterator.hasNext()) {
            AbstractCard c = iterator.next();
            if (c.cardID.equals(LongGame.ID)) {
                iterator.remove();
            }
        }
        return __retVal;
    }
}
