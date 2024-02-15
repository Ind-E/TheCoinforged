package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class LuckyEight extends BaseCard {
    private static final int BLOCK = 8;
    private static final int ENERGY = 2;
    private static final int UPG_ENERGY = 1;

    public static final String ID = makeID(LuckyEight.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public LuckyEight() {
        super(ID, info);
        setBlock(BLOCK);
        setMagic(ENERGY, UPG_ENERGY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsPlayedThisTurn = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        if (cardsPlayedThisTurn == 8) {
            addToBot(new DrawCardAction(8));
            addToBot(new GainEnergyAction(this.magicNumber));
        }
        addToBot(new GainBlockAction(p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 7) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LuckyEight();
    }
}