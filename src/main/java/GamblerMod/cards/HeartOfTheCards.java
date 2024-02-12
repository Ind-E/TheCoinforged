package GamblerMod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import GamblerMod.actions.DiscardDrawAction;
import GamblerMod.actions.HeartOfTheCardsAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

//TODO: rework/balance
public class HeartOfTheCards extends BaseCard {
    private static final int MAGIC = -1;
    private static final int UPG_MAGIC = 1;
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
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = AbstractDungeon.player.hand.size();
        if (count != 0) {
            addToTop(new DiscardDrawAction(this.magicNumber));
            addToBot(new HeartOfTheCardsAction());
            if (!this.freeToPlayOnce)
                p.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeartOfTheCards();
    }
}
