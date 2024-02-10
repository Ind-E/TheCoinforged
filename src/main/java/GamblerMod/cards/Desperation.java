package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.DesperationAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class Desperation extends BaseCard{
    private static final int CARD_DRAW = 4;

    public static final String ID = makeID(Desperation.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL, 
        CardRarity.UNCOMMON, 
        CardTarget.NONE, 
        0 
    );

    public Desperation() {
        super(ID, info);
        setMagic(CARD_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new DesperationAction(this.upgraded));
    }

}
