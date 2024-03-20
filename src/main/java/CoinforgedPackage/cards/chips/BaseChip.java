package CoinforgedPackage.cards.chips;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public abstract class BaseChip extends AbstractCoinforgedCard {
    public static final int DRAW = 1;
    public static final int UPG_DRAW = 1;
    protected boolean canUse = false;

    public static String ID = makeID(BaseChip.class.getSimpleName());
    protected static CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2);

    public BaseChip() {
        this(ID, info);
    }

    public BaseChip(String ID, CardStats info) {
        super(ID, info);
        tags.add(CustomTags.POKER_CHIP);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return canUse;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new DrawCardAction(AbstractDungeon.player, 1));
    }
}