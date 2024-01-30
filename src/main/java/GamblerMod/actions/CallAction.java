package GamblerMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class CallAction extends AbstractGameAction{
    private AbstractCard targetCard;
    private int magic, damage, energy;
    private AbstractPlayer p = AbstractDungeon.player;

    public CallAction(AbstractCard targetCard, int damage, int magic, int energy) {
        super();
        this.targetCard = targetCard;
        this.magic = magic;
        this.damage = damage;
        this.energy = energy;
    }

    public void update() {
        if (p.discardPile.getTopCard().type == AbstractCard.CardType.SKILL) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
                addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, magic, false), magic, true, AbstractGameAction.AttackEffect.NONE)); 
        } else if (p.discardPile.getTopCard().type == AbstractCard.CardType.ATTACK) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage), AbstractGameAction.AttackEffect.NONE));
        } else if (p.discardPile.getTopCard().type == AbstractCard.CardType.POWER) {
            addToBot(new GainEnergyAction(energy));
        }
        this.isDone = true;
    }
}
