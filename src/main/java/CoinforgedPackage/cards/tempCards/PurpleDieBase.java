package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

import CoinforgedPackage.CoinforgedMod;
import CoinforgedPackage.cards.BaseCard;
import CoinforgedPackage.util.CardStats;

public abstract class PurpleDieBase extends BaseCard {
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(PurpleDieBase.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0);

    public PurpleDieBase(String ID, int MAGIC) {
        this(ID, info, MAGIC, UPG_MAGIC);
    }

    public PurpleDieBase(String ID, CardStats info, int MAGIC, int UPG_MAGIC) {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
        tags.add(CoinforgedMod.DIE);
        tags.add(CoinforgedMod.PURPLE_DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction((AbstractCreature) p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        } else {
            addToBot(new VFXAction(p, new FlameBarrierEffect(p.hb.cX, p.hb.cY), 0.4F));
        }
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber), this.magicNumber));
    }

}
