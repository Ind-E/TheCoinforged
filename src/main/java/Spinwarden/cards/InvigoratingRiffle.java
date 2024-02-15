package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.InvigoratingRifflePower;
import Spinwarden.util.CardStats;

public class InvigoratingRiffle extends BaseCard {
    private static final int VIGOR = 2;
    private static final int UPG_VIGOR = 1;

    public static final String ID = makeID(InvigoratingRiffle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public InvigoratingRiffle() {
        super(ID, info);
        setMagic(VIGOR, UPG_VIGOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new InvigoratingRifflePower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new InvigoratingRiffle();
    }
}