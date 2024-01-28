package GamblerMod.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.cards.BaseCard;
import GamblerMod.util.CardStats;
import GamblerMod.GamblerMod;

public class ChaosThree extends BaseCard{
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(ChaosThree.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS, 
            CardType.SKILL, 
            CardRarity.SPECIAL, 
            CardTarget.SELF, 
            0 
    );

    public ChaosThree() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
        tags.add(GamblerMod.MAGIC_DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(this.magicNumber));
    }

}
