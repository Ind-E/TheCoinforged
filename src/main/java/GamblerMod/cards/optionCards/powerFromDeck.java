package GamblerMod.cards.optionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.addTypeToHandAction;
import GamblerMod.cards.BaseCard;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class powerFromDeck extends BaseCard{

    public static final String ID = makeID(powerFromDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.POWER,
        CardRarity.SPECIAL,
        CardTarget.NONE,
        -2
    );

    public powerFromDeck() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        addToBot(new addTypeToHandAction(this.type));
    }
}