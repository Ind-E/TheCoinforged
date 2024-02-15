package Spinwarden.cards.optionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.actions.AddTypeToHandAction;
import Spinwarden.cards.BaseCard;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class PowerFromDeck extends BaseCard {

    public static final String ID = makeID(PowerFromDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2);

    public PowerFromDeck() {
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