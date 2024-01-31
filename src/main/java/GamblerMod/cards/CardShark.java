package GamblerMod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class CardShark extends BaseCard{
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 4;
    private AbstractPlayer p = AbstractDungeon.player;
    private int baseBaseDamage = 8;

    public static final String ID = makeID(CardShark.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.COMMON, 
            CardTarget.ENEMY, 
            2 
    );

    public CardShark() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
    }

    public void upgrade() {
        if (!upgraded) {
        super.upgrade();
        baseBaseDamage += 4;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.getBottomCard().uuid == this.uuid) {
            if (Settings.FAST_MODE) {
                addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
                for (int i = 0; i < 5; i++)
                    addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY))); 
            } else {
                addToBot(new VFXAction(new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
                for (int i = 0; i < 5; i++)
                    addToBot(new VFXAction(new StarBounceEffect(m.hb.cX, m.hb.cY))); 
            } 
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        } else {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void triggerOnGlowCheck() {
        p = AbstractDungeon.player;
        if (p.hand.getBottomCard().uuid == this.uuid) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            this.costForTurn = 0;
            this.damage = 2 * this.baseBaseDamage;
            this.baseDamage = 2 * this.baseBaseDamage;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            if (!this.isCostModified)
                this.costForTurn = baseCost;
            this.damage = this.baseBaseDamage;
            this.baseDamage = this.baseBaseDamage;
        } 
    }

    @Override
    public void onMoveToDiscard() {
        this.damage = this.baseBaseDamage;
        this.baseDamage = this.baseBaseDamage;
    }

}
