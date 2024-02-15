package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.LoadedDicePower;
import Spinwarden.util.CardStats;

public class LoadedDice extends BaseCard {
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(LoadedDice.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public LoadedDice() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LoadedDicePower(p, magicNumber)));
    }
}