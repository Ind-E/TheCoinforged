package GamblerMod.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import GamblerMod.cards.BaseCard;
import GamblerMod.util.CardStats;
import GamblerMod.GamblerMod;

public class ChaosOne extends BaseCard{
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(ChaosOne.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS, 
            CardType.SKILL, 
            CardRarity.SPECIAL, 
            CardTarget.SELF, 
            0 
    );

    public ChaosOne() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
        tags.add(GamblerMod.MAGIC_DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
    }

}
