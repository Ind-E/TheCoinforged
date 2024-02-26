package Spinwarden.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

// import static Spinwarden.SpinwardenMain.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.SpinwardenMain;
import Spinwarden.cards.BaseCard;
import Spinwarden.util.CardStats;

public class PokerChip extends BaseCard {

    public static final String ID = makeID(PokerChip.class.getSimpleName());
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2);

    public PokerChip() {
        super(ID, info);
        tags.add(SpinwardenMain.POKER_CHIP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return upgraded;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.selfRetain = true;
        }
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