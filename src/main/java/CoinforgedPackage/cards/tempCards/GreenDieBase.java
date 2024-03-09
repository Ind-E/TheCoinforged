package CoinforgedPackage.cards.tempCards;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect.ShockWaveType;

import CoinforgedPackage.cards.BaseCard;
import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.util.CardStats;

public abstract class GreenDieBase extends BaseCard {
   private static final int UPG_MAGIC = 2;

   public static final String ID = makeID(GreenDieBase.class.getSimpleName());
   public static final CardStats info = new CardStats(
         CardColor.COLORLESS,
         CardType.SKILL,
         CardRarity.SPECIAL,
         CardTarget.ALL_ENEMY,
         0);

   public GreenDieBase(String ID, int MAGIC) {
      this(ID, info, MAGIC, UPG_MAGIC);
   }

   public GreenDieBase(String ID, CardStats info, int MAGIC, int UPG_MAGIC) {
      super(ID, info);
      setMagic(MAGIC, UPG_MAGIC);
      this.exhaust = true;
      tags.add(CustomTags.DIE);
      tags.add(CustomTags.GREEN_DIE);
   }

   @Override
   public void use(AbstractPlayer p, AbstractMonster m) {
      this.addToBot(new SFXAction("ATTACK_PIERCING_WAIL"));
      if (Settings.FAST_MODE) {
         this.addToBot(new VFXAction(p,
               new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveType.CHAOTIC), 0.3F));
      } else {
         this.addToBot(new VFXAction(p,
               new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveType.CHAOTIC), 1.5F));
      }

      Iterator<AbstractMonster> var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

      AbstractMonster mo;
      while (var3.hasNext()) {
         mo = (AbstractMonster) var3.next();
         this.addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber, true,
               AttackEffect.NONE));
      }

      var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

      while (var3.hasNext()) {
         mo = (AbstractMonster) var3.next();
         if (!mo.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(mo, p, new GainStrengthPower(mo, this.magicNumber), this.magicNumber,
                  true, AttackEffect.NONE));
         }
      }
   }

}
