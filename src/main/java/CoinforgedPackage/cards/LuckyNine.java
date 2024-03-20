package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class LuckyNine extends AbstractCoinforgedCard {
    private static final int MAGIC = 9;

    public static final String ID = makeID(LuckyNine.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2);

    public LuckyNine() {
        super(ID, info);
        setMagic(MAGIC);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(p, magicNumber, false), magicNumber));
        }
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 9) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 9), 9));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 8) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    @Override
    public LuckyNine makeCopy() {
        return new LuckyNine();
    }

}