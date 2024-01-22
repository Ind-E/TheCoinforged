package GamblerMod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

import static GamblerMod.GamblerMod.makeID;

public class GamblePower extends BasePower{
    public static final String POWER_ID = makeID("GamblePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private int gold = 0;

    public GamblePower(AbstractCreature owner, int gold) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 2);
        this.gold = gold;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(this.owner, this.owner, makeID("GamblePower"), 1));
    }

    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.amount == 1) {
            p.gainGold(gold);
            for (int i = 0; i < gold; i++)
                AbstractDungeon.effectList.add(new GainPennyEffect(this.owner, this.owner.hb.cX, this.owner.hb.cY, this.owner.hb.cX, this.owner.hb.cY, true)); 
        }
        
    }

}
