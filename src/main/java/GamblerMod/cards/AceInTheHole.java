package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.AceInTheHoleAction;
import GamblerMod.actions.HeartOfTheCardsAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class AceInTheHole extends BaseCard{
    private static final int CARDS_TO_SEEK = 1;

    public static final String ID = makeID(AceInTheHole.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.NONE,
        0
    );

    public AceInTheHole() {
        super(ID, info);
        this.purgeOnUse = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new AceInTheHoleAction(CARDS_TO_SEEK, upgraded));
        addToTop(new HeartOfTheCardsAction());
        addToTop(new DiscardAction(p, p, p.hand.size(), false));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new AceInTheHole();
    }
}