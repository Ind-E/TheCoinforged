package GamblerMod.cards.optionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.addTypeToHandAction;
import GamblerMod.cards.BaseCard;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class skillFromDeck extends BaseCard{

    public static final String ID = makeID(skillFromDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.SPECIAL,
        CardTarget.NONE,
        -2
    );

    public skillFromDeck() {
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