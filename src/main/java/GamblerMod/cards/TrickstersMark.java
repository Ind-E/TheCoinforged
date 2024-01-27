package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerToRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import GamblerMod.character.Gambler;

import GamblerMod.util.CardStats;

public class TrickstersMark extends BaseCard{
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(TrickstersMark.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.ALL_ENEMY, 
            1 
    );

    public TrickstersMark() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // desired action is to apply 2 Vuln + Weak to the same enemy, currently randomizes each
        addToBot(new ApplyPowerToRandomEnemyAction(p, new VulnerablePower(p, this.magicNumber, false), this.magicNumber));
        addToBot(new ApplyPowerToRandomEnemyAction(p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
    }

}
