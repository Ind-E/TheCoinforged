package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import GamblerMod.character.Gambler;
import GamblerMod.powers.SnakeEyesPower;
import GamblerMod.util.CardStats;

public class SnakeEyes extends BaseCard{
    private static final int MAGIC = 2;

    public static final String ID = makeID(SnakeEyes.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL, 
        CardRarity.UNCOMMON, 
        CardTarget.SELF, 
        2 
    );

    public SnakeEyes() {
        super(ID, info);
        setMagic(MAGIC);
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SnakeEyesPower(p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1)));
    }

}
