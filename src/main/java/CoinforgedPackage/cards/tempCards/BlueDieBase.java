package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.CoinforgedMod;
import CoinforgedPackage.cards.BaseCard;
import CoinforgedPackage.util.CardStats;

public abstract class BlueDieBase extends BaseCard {
    private static final int UPG_BLOCK = 3;

    public static final String ID = makeID(BlueDieBase.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0);

    public BlueDieBase(String ID, int BLOCK) {
        this(ID, info, BLOCK, UPG_BLOCK);
    }

    public BlueDieBase(String ID, CardStats info, int BLOCK, int UPG_BLOCK) {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        this.exhaust = true;
        tags.add(CoinforgedMod.DIE);
        tags.add(CoinforgedMod.BLUE_DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
    }

}
