package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class BrokenBottleAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private int damage;
    private DamageInfo.DamageType damageType;
    private int energyOnUse = -1;
    private AbstractCreature target;
    private AbstractPlayer p = AbstractDungeon.player;

    public BrokenBottleAction(AbstractCreature target, int damage, DamageInfo.DamageType damageType,
            boolean freeToPlayOnce, int energyOnUse) {
        this.target = target;
        this.damage = damage;
        this.damageType = damageType;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;

    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic(ChemicalX.ID)) {
            effect += 2;
            this.p.getRelic(ChemicalX.ID).flash();
        }
        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                addToBot(new SFXAction("ATTACK_HEAVY"));
                addToBot(new VFXAction(this.p, new CleaveEffect(), 0.0F));
                addToBot(new DamageAction(target, new DamageInfo(this.p, this.damage, this.damageType),
                        AbstractGameAction.AttackEffect.NONE, true));
            }
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        addToBot(new ApplyPowerAction(target, p, new VulnerablePower(p, effect, false)));
        this.isDone = true;
    }
}