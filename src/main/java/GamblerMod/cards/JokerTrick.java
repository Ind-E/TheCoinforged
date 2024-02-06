package GamblerMod.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoiceBuilder;
import basemod.helpers.TooltipInfo;

// Effect: Choose a card type (Attack, Skill, Power) and play a random card of that type from your draw pile for free.
public class JokerTrick extends BaseCard implements ModalChoice.Callback{
    public static final String ID = makeID(JokerTrick.class.getSimpleName());

    private ModalChoice modal;

    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.NONE,
        1
    );

    public JokerTrick() {
        super(ID, info);
        this.exhaust = true;

        modal = new ModalChoiceBuilder()
                .setCallback(this) // Sets callback of all the below options to this
                .setColor(CardColor.COLORLESS)
                .setType(AbstractCard.CardType.ATTACK)
                .addOption("Attack", "Add a random Attack card from your draw pile to your hand. NL It costs 0 this turn.", CardTarget.NONE)
                .setType(AbstractCard.CardType.SKILL)
                .addOption("Skill", "Add a random Green card from your draw pile to your hand. NL It costs 0 this turn.", CardTarget.NONE)
                .setType(AbstractCard.CardType.POWER)
                .addOption("Power", "Add a random Colorless card from your draw pile to your hand. NL It costs 0 this turn.", CardTarget.NONE)
                .create();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.exhaust = false;
        }
    }

    // Uses the titles and descriptions of the option cards as tooltips for this card
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return modal.generateTooltips();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        modal.open();
    }

    // This is called when one of the option cards us chosen
    @Override
    public void optionSelected(AbstractPlayer p, AbstractMonster m, int i) {
        CardType type;
        switch (i) {
            case 0:
                type = CardType.ATTACK;
                break;
            case 1:
                type = CardType.SKILL;
                break;
            case 2:
                type = CardType.POWER;
                break;
            default:
                return;
        }

        AbstractCard cardToDraw;
        ArrayList<AbstractCard> possibleCards = new ArrayList<AbstractCard>();
        for (AbstractCard c : p.hand.group) {
            if (c.type == type) {
                possibleCards.add(c);
            }
        }
        cardToDraw = possibleCards.get(ThreadLocalRandom.current().nextInt(0,possibleCards.size() + 1));

        if (p.hand.size() == 10) {
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