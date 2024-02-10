package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.character.Gambler;
import GamblerMod.powers.LuckOfTheDrawPower;
import GamblerMod.util.CardStats;

public class LuckOfTheDraw extends BaseCard{
    private static final int MAGIC = 1;

    public static final String ID = makeID(LuckOfTheDraw.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.POWER, 
        CardRarity.RARE, 
        CardTarget.SELF, 
        2 
    );

    public LuckOfTheDraw() {
        super(ID, info); 
        setMagic(MAGIC);
    }

    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            upgradeBaseCost(1);
        } 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LuckOfTheDrawPower(p, this.magicNumber, 1)));
    }

}
