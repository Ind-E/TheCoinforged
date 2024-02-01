package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;

import GamblerMod.util.CardStats;

public class Rig extends BaseCard{

    public static final String ID = makeID(Rig.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.NONE, 
            0 
    );

    public Rig() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : p.hand.group) {
            c.setCostForTurn(0);
        }
        for (AbstractCard c : p.discardPile.group) {
            c.setCostForTurn(0);
        }
        if (upgraded) {
            addToBot(new DrawCardAction(p, 2));
        }
    }

}
