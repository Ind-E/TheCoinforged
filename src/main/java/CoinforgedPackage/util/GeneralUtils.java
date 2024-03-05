package CoinforgedPackage.util;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import CoinforgedPackage.CoinforgedMod;

public class GeneralUtils {
    public static String arrToString(Object[] arr) {
        if (arr == null)
            return null;
        if (arr.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length - 1; ++i) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[arr.length - 1]);
        return sb.toString();
    }

    public static String removePrefix(String ID) {
        return ID.substring(ID.indexOf(":") + 1);
    }

    public static int getNumChips() {
        int chips = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(CoinforgedMod.POKER_CHIP)) {
                chips++;
            }
        }
        return chips;
    }

    public static Color glowForChip(int chips) {
        if (getNumChips() > chips) {
            return Color.GOLD.cpy();
        } else {
            return new Color(0.2F, 0.9F, 1.0F, 0.25F);
        }
    }
}
