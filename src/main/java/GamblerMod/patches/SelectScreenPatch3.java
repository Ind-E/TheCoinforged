package GamblerMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;
import GamblerMod.character.Gambler;

@SpirePatch(clz = HandCardSelectScreen.class, method = "updateSelectedCards")
public class SelectScreenPatch3 {
  @SpireInsertPatch(rloc = 34, localvars = {})
  public static void Insert() {
    if (AbstractDungeon.player.chosenClass == Gambler.Enums.GAMBLER)
      SelectScreenPatch.ResetHand(); 
  }
}
