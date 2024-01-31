package GamblerMod.powers;

import static GamblerMod.GamblerMod.makeID;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChaosEchoPower extends BasePower {
    public static final String POWER_ID = makeID("ChaosEchoPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public ChaosEchoPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
  
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        } 
    }
    
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.source, makeID("ChaosEchoPower")));
    }
  
  public void onUseCard(AbstractCard card, UseCardAction action) {
    if (!card.purgeOnUse && this.amount > 0) {
      flash();
      AbstractMonster m = null;
      if (action.target != null)
        m = (AbstractMonster)action.target; 
      AbstractCard tmp = card.makeSameInstanceOf();
      AbstractDungeon.player.limbo.addToBottom(tmp);
      tmp.current_x = card.current_x;
      tmp.current_y = card.current_y;
      tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
      tmp.target_y = Settings.HEIGHT / 2.0F;
      if (m != null)
        tmp.calculateCardDamage(m); 
      tmp.purgeOnUse = true;
      AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
      this.amount--;
      if (this.amount == 0)
        addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("ChaosEchoPower"))); 
    } 
  }
  
}