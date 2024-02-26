package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.SnakeEyesPower;
import Spinwarden.util.CardStats;

public class SnakeEyes extends BaseCard {
    private static final int POWER_DURATION = 2;

    public static final String ID = makeID(SnakeEyes.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2);

    public SnakeEyes() {
        super(ID, info);
        setMagic(POWER_DURATION);
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SnakeEyesPower(p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SnakeEyes();
    }
}