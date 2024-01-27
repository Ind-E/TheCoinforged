package GamblerMod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class DoubleDownAction extends AbstractGameAction {
  private AbstractPlayer p = AbstractDungeon.player;
  private int block, magic;
  
  public DoubleDownAction(int block, int magic) {
    super();
    this.magic = magic;
    this.block = block;
  }
  public void update() {
    if (p.currentBlock <= block) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magic, false), this.magic, true, AbstractGameAction.AttackEffect.NONE));
        } 
    }
    this.isDone = true;
  }
}
