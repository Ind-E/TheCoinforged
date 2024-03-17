package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CardCascadePower;
import CoinforgedPackage.util.CardStats;

public class CardCascade extends BaseCard {
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(CardCascade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public CardCascade() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CardCascadePower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CardCascade();
    }
}