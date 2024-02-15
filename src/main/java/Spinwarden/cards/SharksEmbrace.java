package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.SharksEmbracePower;
import Spinwarden.util.CardStats;

public class SharksEmbrace extends BaseCard {
    private static final int MAGIC = 1;

    public static final String ID = makeID(SharksEmbrace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public SharksEmbrace() {
        super(ID, info);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SharksEmbracePower(p, this.magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            upgradeBaseCost(0);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SharksEmbrace();
    }
}