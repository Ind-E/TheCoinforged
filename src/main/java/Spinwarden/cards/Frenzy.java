package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EchoPower;

import Spinwarden.actions.IfChipsAction;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class Frenzy extends BaseCard {
    private static final int CHIPS = 2;

    public static final String ID = makeID(Frenzy.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0);

    public Frenzy() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IfChipsAction(CHIPS, new ApplyPowerAction(p, p, new EchoPower(p, 1))));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.selfRetain = true;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Frenzy();
    }
}