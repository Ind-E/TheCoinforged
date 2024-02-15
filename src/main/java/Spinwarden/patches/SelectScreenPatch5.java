package Spinwarden.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;

import Spinwarden.character.SpinwardenCharacter;

@SpirePatch(clz = HandCardSelectScreen.class, method = "selectHoveredCard")
public class SelectScreenPatch5 {
  @SpirePostfixPatch
  public static void SelectionPostPatch(HandCardSelectScreen reg) {
    if (AbstractDungeon.player.chosenClass == SpinwardenCharacter.Enums.Spinwarden)
      SelectScreenPatch.ResetHand();
  }
}
