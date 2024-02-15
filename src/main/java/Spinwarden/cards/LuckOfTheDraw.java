package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.LuckOfTheDrawPower;
import Spinwarden.util.CardStats;

public class LuckOfTheDraw extends BaseCard {
    private static final int DAMAGE_ON_DRAW_CARD = 1;
    private static final int BLOCK_ON_DRAW_CARD = 1;

    public static final String ID = makeID(LuckOfTheDraw.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2);

    public LuckOfTheDraw() {
        super(ID, info);
        setMagic(DAMAGE_ON_DRAW_CARD);
    }

    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LuckOfTheDrawPower(p, this.magicNumber, BLOCK_ON_DRAW_CARD)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LuckOfTheDraw();
    }
}
