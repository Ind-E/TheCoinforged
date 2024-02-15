package Spinwarden.cards;

import java.util.ArrayList;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.cards.optionCards.AttackFromDeck;
import Spinwarden.cards.optionCards.PowerFromDeck;
import Spinwarden.cards.optionCards.SkillFromDeck;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class JokerTrick extends BaseCard {
    public static final String ID = makeID(JokerTrick.class.getSimpleName());

    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public JokerTrick() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardChoices = new ArrayList<>();
        cardChoices.add(new AttackFromDeck());
        cardChoices.add(new SkillFromDeck());
        cardChoices.add(new PowerFromDeck());
        addToBot(new ChooseOneAction(cardChoices));
    }

    @Override
    public AbstractCard makeCopy() {
        return new JokerTrick();
    }
}