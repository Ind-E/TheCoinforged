package CoinforgedPackage.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.cards.CustomTags;

@SpirePatch(clz = SingleCardViewPopup.class, method = "allowUpgradePreview")
public class PokerChipsNoUpgradePatch {
    @SpirePrefixPatch
    public static SpireReturn<Boolean> Prefix(SingleCardViewPopup __instance, AbstractCard ___card) {
        if (___card.hasTag(CustomTags.POKER_CHIP) || ___card instanceof AbstractCoinforgedCard && ___card.upgraded) {
            return SpireReturn.Return(false);
        }
        return SpireReturn.Continue();
    }
}