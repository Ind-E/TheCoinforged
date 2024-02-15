package Spinwarden.cards.optionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.actions.AddTypeToHandAction;
import Spinwarden.cards.BaseCard;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class AttackFromDeck extends BaseCard {

    public static final String ID = makeID(AttackFromDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2);

    public AttackFromDeck() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        addToBot(new AddTypeToHandAction(this.type));
    }
}