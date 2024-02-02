package GamblerMod.cards;

import GamblerMod.GamblerMod;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class Jackpot extends BaseCard{
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 5;
    public static final String ID = makeID(Jackpot.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL, 
        CardRarity.RARE, 
        CardTarget.NONE, 
        0 
    );

    public Jackpot() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
        this.retain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.group.size() == 0) {
        addToBot(new DrawCardAction(p, MAGIC));
        addToBot(new GainEnergyAction(2));
        }
    }

}