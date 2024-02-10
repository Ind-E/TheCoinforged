package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.RedKingPower;
import GamblerMod.util.CardStats;
import GamblerMod.actions.RollRedAction;

public class RedKing extends BaseCard{
    private static final int DICE_TO_ROLL = 2;
    private static final int UPG_DICE_TO_ROLL = 2;
    
    public static final String ID = makeID(RedKing.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.POWER, 
        CardRarity.UNCOMMON, 
        CardTarget.SELF, 
        1 
    );

    public RedKing() {
        super(ID, info);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(RedKing.ID))
            addToBot(new ApplyPowerAction(p, p, new RedKingPower(p)));
        addToBot(new RollRedAction(p, this.magicNumber));
    }

}
