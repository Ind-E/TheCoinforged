package GamblerMod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.RollBlueAction;
import GamblerMod.actions.RollRedAction;
import GamblerMod.actions.RollGreenAction;
import GamblerMod.actions.RollPurpleAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class PrismaticRoll extends BaseCard{
    private static final int MAGIC = 1;

    public static final String ID = makeID(PrismaticRoll.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.NONE,
        2
    );

    public PrismaticRoll() {
        super(ID, info);
        setMagic(MAGIC);
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
}