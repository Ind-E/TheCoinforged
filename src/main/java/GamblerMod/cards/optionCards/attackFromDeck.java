package GamblerMod.cards.optionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.addTypeToHandAction;
import GamblerMod.cards.BaseCard;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class attackFromDeck extends BaseCard{

    public static final String ID = makeID(attackFromDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.SPECIAL,
        CardTarget.NONE,
        -2
    );

    public attackFromDeck() {
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