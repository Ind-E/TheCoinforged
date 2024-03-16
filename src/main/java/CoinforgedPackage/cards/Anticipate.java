package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.RollPurpleAction;
import CoinforgedPackage.character.Coinforged;

//roll a purple die. Next turn, gain 10 block.
// card art idea: laying caltrops in front of sleeping lagavulin
public class Anticipate extends BaseCard{
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 4;

    public static final String ID = makeID(Anticipate.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        1
    );

    public Anticipate() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollPurpleAction(p, 1));
        addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), 1));
    }
}