package CoinforgedPackage.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.powers.CripplingDebtPower;

@SpirePatch(clz = AbstractMonster.class, method = "damage")
public class CripplingDebtPatch {

    @SpirePostfixPatch
    public static void CripplingDebtPostPatch(AbstractMonster __instance) {
        if (!__instance.isDying && !__instance.isEscaping && !__instance.isDead && !__instance.halfDead) {
            if (__instance.hasPower(CripplingDebtPower.POWER_ID)) {
                CripplingDebtPower power = (CripplingDebtPower) __instance.getPower(CripplingDebtPower.POWER_ID);
                power.checkExplode();
            }
        }
    }

}
