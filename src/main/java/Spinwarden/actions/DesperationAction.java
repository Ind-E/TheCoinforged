package Spinwarden.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DesperationAction extends AbstractGameAction {
  private AbstractPlayer p = AbstractDungeon.player;
  private boolean upgraded;

  public DesperationAction(boolean upgraded) {
    super();
    this.upgraded = upgraded;
  }

  public void update() {
    for (AbstractCard c : this.p.hand.group) {
      if (!upgraded || upgraded && c.cost < 2) {
        c.setCostForTurn(c.costForTurn + 1);
        c.superFlash(Color.RED.cpy());
      }
    }
    tickDuration();
  }
}
