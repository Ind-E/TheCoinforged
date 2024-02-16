package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.FoolsFortunePower;
import Spinwarden.util.CardStats;

public class FoolsFortune extends BaseCard {
    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;
    private static final int ENERGY_NEXT_TURN = 1;

    public static final String ID = makeID(FoolsFortune.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public FoolsFortune() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FoolsFortunePower(p, ENERGY_NEXT_TURN, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FoolsFortune();
    }
}
