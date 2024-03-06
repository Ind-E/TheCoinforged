package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.GamblePower;
import CoinforgedPackage.util.CardStats;

public class RiskyInvestment extends BaseCard {
    private static final int GOLD_GAIN = 75;
    private static final int UPG_GOLD_GAIN = 25;

    public static final String ID = makeID(RiskyInvestment.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1);

    public RiskyInvestment() {
        super(ID, info);
        setMagic(GOLD_GAIN, UPG_GOLD_GAIN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.loseGold(10);
        addToBot(new ApplyPowerAction(p, p, new GamblePower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RiskyInvestment();
    }
}
