package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

import CoinforgedPackage.actions.IfChipsAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class GlassShield extends BaseCard {
    private static final int CHIPS = 1;
    private static final int MAGIC = 1;

    public static final String ID = makeID(GlassShield.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public GlassShield() {
        super(ID, info);
        setMagic(MAGIC);
        setEthereal(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IfChipsAction(CHIPS, new ApplyPowerAction(p, p, new BufferPower(p, this.magicNumber))));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (getNumChips() >= this.magicNumber) {
            this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GlassShield();
    }
}