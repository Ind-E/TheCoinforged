package Spinwarden.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.actions.RollBlueAction;
import Spinwarden.actions.RollGreenAction;
import Spinwarden.actions.RollPurpleAction;
import Spinwarden.actions.RollRedAction;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class PrismaticRoll extends BaseCard {
    private static final int DICE_TO_ROLL = 1;

    public static final String ID = makeID(PrismaticRoll.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            2);

    public PrismaticRoll() {
        super(ID, info);
        setMagic(DICE_TO_ROLL);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.exhaust = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollRedAction(p, this.magicNumber));
        addToBot(new RollBlueAction(p, this.magicNumber));
        addToBot(new RollGreenAction(p, this.magicNumber));
        addToBot(new RollPurpleAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrismaticRoll();
    }
}