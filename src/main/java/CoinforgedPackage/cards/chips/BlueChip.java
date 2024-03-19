package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlueChip extends BaseChip {

    public static final String ID = makeID(BlueChip.class.getSimpleName());

    private static final int BLOCK = 3;

    public BlueChip() {
        super(ID, info);
        this.cost = this.costForTurn = 0;
        this.canUse = true;
        setBlock(BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
    }
}