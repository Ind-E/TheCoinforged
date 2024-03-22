package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

//TODO: rethink
public class StrategistsShield extends AbstractCoinforgedCard {
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    public static final String ID = makeID(StrategistsShield.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public StrategistsShield() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        int powers = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.POWER || upgraded && (c.type != AbstractCard.CardType.ATTACK && c.type != AbstractCard.CardType.SKILL)) {
                powers++;
            }
        }
        if (powers > 0)
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, powers)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrategistsShield();
    }
}
