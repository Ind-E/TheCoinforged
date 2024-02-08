package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.RollGreenAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class PocketPair extends BaseCard{
    private static final int MAGIC = 2;

    public static final String ID = makeID(PocketPair.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.NONE,
        1
    );

    public PocketPair() {
        super(ID, info);
        setMagic(MAGIC);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        RollGreenAction green = new RollGreenAction(p, 1);
        AbstractCard c1 = green.roll(), c2 = green.roll();
        if (upgraded) {
            c1.selfRetain = true;
            c2.selfRetain = true;
        }
        addToBot(new MakeTempCardInHandAction(c1));
        addToBot(new MakeTempCardInHandAction(c2));
        
    }
}