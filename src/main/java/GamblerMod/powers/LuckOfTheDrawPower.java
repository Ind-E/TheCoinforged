package GamblerMod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;

import static GamblerMod.GamblerMod.makeID;

import java.util.concurrent.ThreadLocalRandom;

public class LuckOfTheDrawPower extends BasePower{
    public static final String POWER_ID = makeID("LuckOfTheDrawPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    
    private AbstractPlayer p = AbstractDungeon.player;

    public LuckOfTheDrawPower(AbstractCreature owner, int damage, int block) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        int rand = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        if (rand == 0) {
            addToBot(new GainBlockAction(owner, owner, this.amount));
        } else if (rand == 1) {
            AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
            if (m != null) {
                addToBot(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.15F));
                addToBot(new DamageAction(m, new DamageInfo(owner, this.amount, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Luck Of the Draw != 0,1");
        }
    }

}
