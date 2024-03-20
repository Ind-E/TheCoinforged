package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.DeadlyWagerPower;
import CoinforgedPackage.powers.StrengthOnlyBuffPower;
import CoinforgedPackage.util.CardStats;

public class DeadlyWager extends AbstractCoinforgedCard {
    private static final int ENEMY_STRENGTH_LOSS = 6;
    private static final int UPG_ENEMY_STRENGTH_LOSS = 2;
    private static final int PLAYER_HP_LOSS = 2;

    public static final String ID = makeID(DeadlyWager.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2);

    public DeadlyWager() {
        super(ID, info);
        setMagic(ENEMY_STRENGTH_LOSS, UPG_ENEMY_STRENGTH_LOSS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
                addToBot(new ApplyPowerAction(mo, p, new StrengthOnlyBuffPower(mo, -this.magicNumber),
                        -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        } else {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
                addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber, true,
                        AbstractGameAction.AttackEffect.NONE));
        }
        addToBot(new ApplyPowerAction(p, p, new DeadlyWagerPower(p, PLAYER_HP_LOSS)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DeadlyWager();
    }
}
