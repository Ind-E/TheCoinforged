package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.cards.tempCards.Bond;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;

public class DebtTransfer extends AbstractCoinforgedCard {

    public static final String ID = makeID(DebtTransfer.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0);

    public DebtTransfer() {
        super(ID, info);
        this.cardsToPreview = new Bond(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower debt = m.getPower(CripplingDebtPower.POWER_ID);
        if (debt != null) {
            addToBot(new RemoveSpecificPowerAction(m, p, CripplingDebtPower.POWER_ID));
            addToBot(new MakeTempCardInHandAction(new Bond(debt.amount)));
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                    AbstractDungeon.player.dialogY, 3.0F, cardStrings.EXTENDED_DESCRIPTION[0], true));
        }
    }
}