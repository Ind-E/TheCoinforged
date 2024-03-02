package CoinforgedPackage.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.cards.LongGame;
import CoinforgedPackage.powers.LongGamePower;

@SpirePatch(clz = CardGroup.class, method = "initializeDeck")
public class LongGamePatch {
    @SpirePostfixPatch()
    public static void PostFix(CardGroup __instance) {
        AbstractPlayer p = AbstractDungeon.player;
        for (Iterator<AbstractCard> i = __instance.group.iterator(); i.hasNext();) {
            AbstractCard card = i.next();
            if (card instanceof LongGame) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(p, p, new LongGamePower(p, card.magicNumber, card)));
                i.remove();
            }
        }
    }
}
