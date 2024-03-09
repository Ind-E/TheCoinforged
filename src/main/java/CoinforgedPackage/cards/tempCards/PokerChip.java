package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.BaseCard;
import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class PokerChip extends BaseCard {

    public static final String ID = makeID(PokerChip.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2);

    public PokerChip() {
        super(ID, info);
        tags.add(CustomTags.POKER_CHIP);
        setSelfRetain(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return upgraded;
    }

    @Override
    public void triggerWhenDrawn() {
        addToBot(new DrawCardAction(AbstractDungeon.player, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PokerChip();
    }
}