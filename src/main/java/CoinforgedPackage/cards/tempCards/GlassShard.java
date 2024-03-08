package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.cards.BaseCard;
import CoinforgedPackage.character.Coinforged;

public class GlassShard extends BaseCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(GlassShard.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0);

    public GlassShard() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(p, this.magicNumber, false)));
    }
}