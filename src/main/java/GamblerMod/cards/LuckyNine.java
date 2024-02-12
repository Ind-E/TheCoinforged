package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class LuckyNine extends BaseCard {
    private static final int MAGIC = 9;

    public static final String ID = makeID(LuckyNine.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1);

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
    public LuckyNine makeCopy() {
        return new LuckyNine();
    }

}