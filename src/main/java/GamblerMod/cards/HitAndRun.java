package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.HitAndRunPower;
import GamblerMod.util.CardStats;

public class HitAndRun extends BaseCard{

    public static final String ID = makeID(HitAndRun.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            2
    );

    public HitAndRun() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }

    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            upgradeBaseCost(1);
        }
        
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(HitAndRunPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new HitAndRunPower(p)));
        }
    }

}
