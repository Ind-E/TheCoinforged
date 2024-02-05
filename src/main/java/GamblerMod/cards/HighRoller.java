package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.HighRollerPower;
import GamblerMod.util.CardStats;

public class HighRoller extends BaseCard{

    public static final String ID = makeID(HighRoller.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    public HighRoller() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HighRollerPower(p, 1)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            upgradeBaseCost(0);
        } 
    }
}