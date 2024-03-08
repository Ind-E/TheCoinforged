package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.SpendChipsAction;
import CoinforgedPackage.character.Coinforged;

//deal 12 dmg to all enemies. Chip 1: apply 2 vulnerable to all enemies
public class Outrage extends BaseCard {
    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 4;
    private static final int MAGIC = 2;
    private static final int CHIPS = 1;

    public static final String ID = makeID(Outrage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2);

    public Outrage() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageType.NORMAL, AttackEffect.SLASH_HORIZONTAL));
        if (getNumChips() >= CHIPS) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber,
                        true, AbstractGameAction.AttackEffect.NONE));
            }
            addToBot(new SpendChipsAction(CHIPS));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (getNumChips() >= CHIPS) {
            this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Outrage();
    }
}