package CoinforgedPackage.cards;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.DrawbackPlayerPower;
import CoinforgedPackage.powers.DrawbackPower;

public class Melon extends AbstractCoinforgedCard {
    private static final int BLOCK = 13;
    private static final int UPG_BLOCK = 4;
    private static final int MAGIC = 1;

    public static final String ID = makeID(Melon.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ALL,
            2);

    public Melon() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(p, p, new DrawbackPlayerPower(p, this.magicNumber), this.magicNumber));

        Iterator<AbstractMonster> monstIterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (monstIterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster) monstIterator.next();
            addToBot(new ApplyPowerAction(mo, p, new DrawbackPower(mo, this.magicNumber), this.magicNumber, true, AttackEffect.NONE));
        }
    }
}