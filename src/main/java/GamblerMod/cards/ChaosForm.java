package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.ChaosFormPower;
import GamblerMod.util.CardStats;

public class ChaosForm extends BaseCard{
    private static final int MAGIC = 1;

    public static final String ID = makeID(ChaosForm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            3 
    );

    public ChaosForm() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MAGIC);
    }

    public void upgrade() {
        super.upgrade();
        if (!this.upgraded) {
            upgradeBaseCost(2);
        } 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ChaosFormPower(p)));
    }

}
