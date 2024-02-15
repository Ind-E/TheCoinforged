package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.PokerFacePower;
import Spinwarden.util.CardStats;

public class PokerFace extends BaseCard {

    public static final String ID = makeID(PokerFace.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2);

    public PokerFace() {
        super(ID, info);
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.exhaust = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PokerFacePower(p, p.currentHealth), 2));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PokerFace();
    }
}
