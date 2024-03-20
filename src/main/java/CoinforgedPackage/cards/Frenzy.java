package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DuplicationPower;

import CoinforgedPackage.actions.IfChipsAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class Frenzy extends AbstractCoinforgedCard {
    private static final int CHIPS = 2;
    private static final int UPG_CHIPS = -1;

    public static final String ID = makeID(Frenzy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0);

    public Frenzy() {
        super(ID, info);
        setMagic(CHIPS, UPG_CHIPS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IfChipsAction(this.magicNumber, new ApplyPowerAction(p, p, new DuplicationPower(p, 1))));
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
        return new Frenzy();
    }
}