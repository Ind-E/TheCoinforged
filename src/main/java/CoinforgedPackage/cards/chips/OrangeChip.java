package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractRandomChipCard.ChipColor;
import CoinforgedPackage.powers.ModifiedHandSizePower;

//TODO: rebalance
public class OrangeChip extends BaseChip {

    public static final String ID = makeID(OrangeChip.class.getSimpleName());

    public OrangeChip() {
        super(ID, info);
        setExhaust(true);
        setMagic(1);
        this.cost = costForTurn = 0;
        this.canUse = true;
        this.chipColor = ChipColor.Orange;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ModifiedHandSizePower(p, 1)));
        addToBot(new DrawCardAction(this.magicNumber));
    }
}