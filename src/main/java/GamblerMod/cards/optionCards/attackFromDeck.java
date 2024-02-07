package GamblerMod.cards.optionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.AddTypeToHandAction;
import GamblerMod.cards.BaseCard;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class AttackFromDeck extends BaseCard{

    public static final String ID = makeID(AttackFromDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.SPECIAL,
        CardTarget.NONE,
        -2
    );

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