package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.actions.FatesHandAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class FatesHand extends BaseCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(FatesHand.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL, 
        CardRarity.UNCOMMON, 
        CardTarget.NONE, 
        1);

    public FatesHand() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(3));
        addToBot(new DiscardAction(p, p, 2, false));
        addToBot(new FatesHandAction(this.magicNumber));
    }

}
