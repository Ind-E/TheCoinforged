package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.HeartOfTheCardsAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class HeartOfTheCards extends BaseCard{
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 5;
    public static final String ID = makeID(HeartOfTheCards.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL, 
        CardRarity.RARE, 
        CardTarget.NONE, 
        -1 
    );

    public HeartOfTheCards() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
    }

    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.selfRetain = true;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = AbstractDungeon.player.hand.size();
        if (count != 0) {
            addToTop(new CalculatedGambleAction(false));
            addToBot(new HeartOfTheCardsAction());
            
        } 
    }

}
