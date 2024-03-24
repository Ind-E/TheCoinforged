package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.MapCircleEffect;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class Underdog extends AbstractCoinforgedCard {
    private static final int DAMAGE = 20;
    private static final int UPG_DAMAGE = 5;
    private static final int MAGIC = 3;

    public static final String ID = makeID(Underdog.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            3);

    public Underdog() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.currentHealth < m.currentHealth) {
            addToBot(new VFXAction(new MapCircleEffect(m.hb.cX, m.hb.cY, 265f)));
            addToBot(new WaitAction(0.5f));
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage * this.magicNumber, DamageInfo.DamageType.NORMAL), AttackEffect.SMASH));
        } else {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AttackEffect.SMASH));
        }
    }

    public void triggerOnGlowCheck() {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (AbstractDungeon.player.currentHealth < mo.currentHealth ) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                return;
            }
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Underdog();
    }
}