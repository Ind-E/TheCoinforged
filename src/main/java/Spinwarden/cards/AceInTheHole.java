package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.actions.AceInTheHoleAction;
import Spinwarden.actions.HandCostsZeroAction;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class AceInTheHole extends BaseCard {
    private static final int CARDS_TO_SEEK = 1;

    public static final String ID = makeID(AceInTheHole.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0);

    public AceInTheHole() {
        super(ID, info);
        this.purgeOnUse = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new AceInTheHoleAction(CARDS_TO_SEEK, upgraded));
        addToTop(new HandCostsZeroAction());
        addToTop(new DiscardAction(p, p, p.hand.size(), false));
    }

    @Override
    public AbstractCard makeCopy() {
        return new AceInTheHole();
    }
}