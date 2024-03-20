package CoinforgedPackage.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.AceInTheHoleAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class AceInTheHole extends AbstractCoinforgedCard {
    private static final int CARDS_TO_SEEK = 1;

    public static final String ID = makeID(AceInTheHole.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0);

    public AceInTheHole() {
        super(ID, info);
        PurgeField.purge.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new DiscardAction(p, p, p.hand.size(), false));
        addToBot(new AceInTheHoleAction(CARDS_TO_SEEK, upgraded));
    }

    @Override
    public AbstractCard makeCopy() {
        return new AceInTheHole();
    }
}