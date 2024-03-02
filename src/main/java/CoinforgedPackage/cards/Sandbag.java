package CoinforgedPackage.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class Sandbag extends BaseCard {
    private static final int BLOCK = 16;
    private static final int UPG_BLOCK = 6;
    private static final int WEAK = 2;

    public static final String ID = makeID(Sandbag.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1);

    public Sandbag() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(WEAK);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.ATTACK) {
            setCostForTurn(this.costForTurn + 1);
            flash(Color.RED.cpy());
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sandbag();
    }
}