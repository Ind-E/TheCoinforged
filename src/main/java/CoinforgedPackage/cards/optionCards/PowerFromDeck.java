package CoinforgedPackage.cards.optionCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.AddTypeToHandAction;
import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class PowerFromDeck extends AbstractCoinforgedCard {

    public static final String ID = makeID(PowerFromDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2);

    public PowerFromDeck() {
        super(ID, info);
    }

    public PowerFromDeck(boolean upgraded) {
        super(ID, info);
        if (upgraded) {
            upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        addToBot(new AddTypeToHandAction(this.type, this.upgraded));
    }
}