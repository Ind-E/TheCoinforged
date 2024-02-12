package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.DoubleDownPower;
import GamblerMod.util.CardStats;

public class DoubleDown extends BaseCard {
    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;
    private static final int VULNERABLE = 2;

    public static final String ID = makeID(DoubleDown.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public DoubleDown() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(VULNERABLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new DoubleDownPower(p, this.magicNumber, this.block)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DoubleDown();
    }
}
