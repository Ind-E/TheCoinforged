package CoinforgedPackage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.localization.CardStrings;

import basemod.abstracts.CustomCard;

import static CoinforgedPackage.util.GeneralUtils.removePrefix;
import static CoinforgedPackage.util.TextureLoader.getCardTextureString;

public class HighCardAction extends AbstractGameAction {
    private int counter;
    private CustomCard card;
    private CardStrings cardStrings;
    public HighCardAction(CustomCard card, CardStrings cardStrings, int counter) {
        super();
        this.counter = counter;
        this.card = card;
        this.cardStrings = cardStrings;
    }

    public void update() {
        if (counter % 2 == 0) {
            card.rawDescription = cardStrings.DESCRIPTION;
            card.type = CardType.ATTACK;
            card.target = CardTarget.ENEMY;
            String img = getCardTextureString(removePrefix(card.cardID), card.type);
            if (img != null) {
                card.loadCardImage(img);
            }
        } else {
            card.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            card.type = CardType.SKILL;
            card.target = CardTarget.NONE;
            String img = getCardTextureString(removePrefix(card.cardID), card.type);
            if (img != null) {
                card.loadCardImage(img);
            }
        }
        card.initializeDescription();
        this.isDone = true;
    }
}