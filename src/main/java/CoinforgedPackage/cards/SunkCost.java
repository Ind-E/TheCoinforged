package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;

// Increase all enemies' Crippling Debt by 50%(75%). Do it again at the end of next turn.
public class SunkCost extends AbstractCoinforgedCard {
    private static final int MAGIC = 50;
    private static final int UPG_MAGIC = 25;

    public static final String ID = makeID(SunkCost.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2);

    public SunkCost() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            AbstractPower debt = mo.getPower(CripplingDebtPower.POWER_ID);
            if (debt != null) {
                addToBot(new ApplyPowerAction(m, p, new CripplingDebtPower(m, (int) (debt.amount * this.magicNumber / 100.0f))));
            }
        }

    }
}