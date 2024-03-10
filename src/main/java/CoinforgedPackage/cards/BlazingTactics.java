package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.actions.IfChipsAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class BlazingTactics extends BaseCard {
    private static final int CARD_DRAW = 3;
    private static final int UPG_CARD_DRAW = 1;
    private static final int CHIPS = 1;
    private static final int ENERGY_GAIN = 1;

    public static final String ID = makeID(BlazingTactics.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0);

    public BlazingTactics() {
        super(ID, info);
        setMagic(CARD_DRAW, UPG_CARD_DRAW);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(ENERGY_GAIN));
        addToBot(new IfChipsAction(CHIPS, new DrawCardAction(p, this.magicNumber)));
    }

    public void triggerOnGlowCheck() {
        if (getNumChips() >= CHIPS) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlazingTactics();
    }
}
