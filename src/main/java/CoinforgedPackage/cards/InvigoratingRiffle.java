package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.InvigoratingRifflePower;
import CoinforgedPackage.util.CardStats;

public class InvigoratingRiffle extends AbstractCoinforgedCard {
    private static final int VIGOR = 2;
    private static final int UPG_VIGOR = 1;

    public static final String ID = makeID(InvigoratingRiffle.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
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