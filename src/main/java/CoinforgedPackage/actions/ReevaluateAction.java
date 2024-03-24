package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReevaluateAction extends AbstractGameAction {

    @Override
    public void update() {
        AbstractCard topCard = AbstractDungeon.player.discardPile.getTopCard();
        if (topCard != null && topCard.type == AbstractCard.CardType.ATTACK) {
            addToTop(new GainBlockAction(AbstractDungeon.player, topCard.damage));
        }
        this.isDone = true;
    }
}
