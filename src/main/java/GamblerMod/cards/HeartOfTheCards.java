package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import GamblerMod.actions.HeartOfTheCardsFollowUpAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class HeartOfTheCards extends BaseCard {
    private static final int X_COST = -1;
    public static final String ID = makeID(HeartOfTheCards.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            X_COST);

    public HeartOfTheCards() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = upgraded ? this.energyOnUse + 1 : this.energyOnUse;
        addToBot(new DrawCardAction(effect, new HeartOfTheCardsFollowUpAction()));
        
        if (!this.freeToPlayOnce)
            p.energy.use(EnergyPanel.totalCount);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeartOfTheCards();
    }
}
