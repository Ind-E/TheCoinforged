package Spinwarden.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.actions.DoubleDealingAction;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class DoubleDealing extends BaseCard {

    public static final String ID = makeID(DoubleDealing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public DoubleDealing() {
        super(ID, info);
        this.exhaust = true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoubleDealingAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new DoubleDealing();
    }
}
