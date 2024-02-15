package Spinwarden.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;

import Spinwarden.character.SpinwardenCharacter;

@SpirePatch(clz = HandCardSelectScreen.class, method = "updateSelectedCards")
public class SelectScreenPatch3 {
  @SpireInsertPatch(rloc = 34, localvars = {})
  public static void Insert() {
    if (AbstractDungeon.player.chosenClass == SpinwardenCharacter.Enums.Spinwarden)
      SelectScreenPatch.ResetHand();
  }
}
