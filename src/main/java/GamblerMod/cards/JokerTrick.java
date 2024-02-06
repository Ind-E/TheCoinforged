package GamblerMod.cards;

import java.util.ArrayList;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.cards.optionCards.attackFromDeck;
import GamblerMod.cards.optionCards.powerFromDeck;
import GamblerMod.cards.optionCards.skillFromDeck;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

// Effect: Choose a card type (Attack, Skill, Power) and play a random card of that type from your draw pile for free.
public class JokerTrick extends BaseCard{
    public static final String ID = makeID(JokerTrick.class.getSimpleName());

    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.NONE,
        1
    );

    public JokerTrick() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.exhaust = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardChoices = new ArrayList<>();
        cardChoices.add(new attackFromDeck());
        cardChoices.add(new skillFromDeck());
        cardChoices.add(new powerFromDeck());
        addToBot(new ChooseOneAction(cardChoices));
    }

    
}