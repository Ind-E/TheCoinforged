package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.FoolsFortunePower;
import GamblerMod.util.CardStats;

public class FoolsFortune extends BaseCard{
    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 2;
    

    public static final String ID = makeID(FoolsFortune.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.POWER, 
        CardRarity.UNCOMMON, 
        CardTarget.SELF, 
        1 
    );

    public FoolsFortune() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FoolsFortunePower(p, 1, this.magicNumber)));
    }

}
