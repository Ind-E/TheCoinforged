package CoinforgedPackage.actions;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.BaseMod;

public class AddTypeToHandAction extends AbstractGameAction {
    private CardType type;

    public AddTypeToHandAction(CardType type) {
        super();
        this.type = type;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard cardToDraw;
        ArrayList<AbstractCard> possibleCards = new ArrayList<AbstractCard>();
        for (AbstractCard c : p.drawPile.group) {
            if (c.type == this.type) {
                possibleCards.add(c);
            }
        }
        if (possibleCards.size() == 0) {
            this.isDone = true;
            return;
        }
        cardToDraw = possibleCards.get(ThreadLocalRandom.current().nextInt(0, possibleCards.size()));

        if (p.hand.size() >= BaseMod.MAX_HAND_SIZE) {
            p.drawPile.moveToDiscardPile(cardToDraw);
            p.createHandIsFullDialog();
        } else {
            cardToDraw.unhover();
            cardToDraw.lighten(true);
            cardToDraw.setAngle(0.0F);
            cardToDraw.drawScale = 0.12F;
            cardToDraw.targetDrawScale = 0.75F;
            cardToDraw.current_x = CardGroup.DRAW_PILE_X;
            cardToDraw.current_y = CardGroup.DRAW_PILE_Y;
            cardToDraw.setCostForTurn(0);
            p.drawPile.removeCard(cardToDraw);
            AbstractDungeon.player.hand.addToTop(cardToDraw);
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
        }
        this.isDone = true;
    }
}