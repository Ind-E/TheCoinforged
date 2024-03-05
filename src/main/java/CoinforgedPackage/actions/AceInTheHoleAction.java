package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import java.util.Iterator;

public class AceInTheHoleAction extends SeekAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean upgraded;

    public AceInTheHoleAction(int amount, boolean upgraded) {
        super(amount);
        this.p = AbstractDungeon.player;
        this.upgraded = upgraded;
    }

    public void update() {
        AbstractCard card1;
        if (this.duration == Settings.ACTION_DUR_MED) {
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
                return;
            }
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);
            AbstractCard card;

            Iterator<AbstractCard> drawPileIterator = this.p.drawPile.group.iterator();
            while (drawPileIterator.hasNext()) {
                card = drawPileIterator.next();
                tmp.addToRandomSpot(card);
            }
            if (upgraded) {
                Iterator<AbstractCard> exhaustPileIterator = this.p.exhaustPile.group.iterator();
                while (exhaustPileIterator.hasNext()) {
                    card = exhaustPileIterator.next();
                    tmp.addToRandomSpot(card);
                }
            }

            if (tmp.size() == 0) {
                this.isDone = true;
            } else if (tmp.size() == 1) {
                card = tmp.getTopCard();
                if (this.p.hand.size() == 10) {
                    this.p.drawPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    if (this.p.drawPile.contains(card))
                        this.p.drawPile.removeCard(card);
                    else if (this.p.exhaustPile.contains(card))
                        this.p.exhaustPile.removeCard(card);

                    card.setCostForTurn(0);
                    AbstractDungeon.player.hand.addToTop(card);
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }

                this.isDone = true;
            } else if (tmp.size() <= this.amount) {
                for (int i = 0; i < tmp.size(); ++i) {
                    card = tmp.getNCardFromTop(i);
                    if (this.p.hand.size() == 10) {
                        this.p.drawPile.moveToDiscardPile(card);
                        this.p.createHandIsFullDialog();
                    } else {
                        card.unhover();
                        card.lighten(true);
                        card.setAngle(0.0F);
                        card.drawScale = 0.12F;
                        card.targetDrawScale = 0.75F;
                        card.current_x = CardGroup.DRAW_PILE_X;
                        card.current_y = CardGroup.DRAW_PILE_Y;
                        if (this.p.drawPile.contains(card))
                            this.p.drawPile.removeCard(card);
                        else if (this.p.exhaustPile.contains(card))
                            this.p.exhaustPile.removeCard(card);
                        card.setCostForTurn(0);
                        AbstractDungeon.player.hand.addToTop(card);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                    }
                }

                this.isDone = true;
            } else {
                if (this.amount == 1) {
                    AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
                } else {
                    AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[1], false);
                }

                this.tickDuration();
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                Iterator<AbstractCard> var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while (var1.hasNext()) {
                    card1 = (AbstractCard) var1.next();
                    card1.unhover();
                    if (this.p.hand.size() == 10) {
                        this.p.drawPile.moveToDiscardPile(card1);
                        this.p.createHandIsFullDialog();
                    } else {
                        if (this.p.drawPile.contains(card1))
                            this.p.drawPile.removeCard(card1);
                        else if (this.p.exhaustPile.contains(card1))
                            this.p.exhaustPile.removeCard(card1);
                        card1.setCostForTurn(0);
                        this.p.hand.addToTop(card1);
                    }

                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
        TEXT = uiStrings.TEXT;
    }
}
