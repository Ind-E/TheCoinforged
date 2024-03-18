package CoinforgedPackage.actions;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class BagOfChipsAction extends AbstractGameAction {

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("BagOfChips"));

    public static final String[] TEXT = uiStrings.TEXT;

    public BagOfChipsAction(AbstractCreature source) {
        super();
        setValues(AbstractDungeon.player, source);
    }

    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);

            addToBot((AbstractGameAction) new WaitAction(0.25F));
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                addToTop((AbstractGameAction) new MakeChipsInHandAction(
                        AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    GameActionManager.incrementDiscard(false);
                    c.triggerOnManualDiscard();
                }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}
