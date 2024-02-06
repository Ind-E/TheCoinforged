package GamblerMod.cards.optionCards;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.cards.BaseCard;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;
import basemod.BaseMod;

public class attackFromDeck extends BaseCard{

    public static final String ID = makeID(attackFromDeck.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.SPECIAL,
        CardTarget.NONE,
        -2
    );

    public attackFromDeck() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard cardToDraw;
        ArrayList<AbstractCard> possibleCards = new ArrayList<AbstractCard>();
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.ATTACK) {
                possibleCards.add(c);
            }
        }
        if (possibleCards.size() == 0)
            return;
        cardToDraw = possibleCards.get(ThreadLocalRandom.current().nextInt(0,possibleCards.size()));

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
          p.drawPile.removeCard(cardToDraw);
          AbstractDungeon.player.hand.addToTop(cardToDraw);
          AbstractDungeon.player.hand.refreshHandLayout();
          AbstractDungeon.player.hand.applyPowers();
        }
        return;
    }
}