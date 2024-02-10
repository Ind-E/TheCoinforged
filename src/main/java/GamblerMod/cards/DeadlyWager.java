package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import GamblerMod.character.Gambler;
import GamblerMod.powers.DeadlyWagerPower;
import GamblerMod.powers.StrengthOnlyBuffPower;
import GamblerMod.util.CardStats;

public class DeadlyWager extends BaseCard{
    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(DeadlyWager.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.POWER, 
        CardRarity.UNCOMMON, 
        CardTarget.ALL_ENEMY, 
        2 
    );

    public DeadlyWager() {
        super(ID, info); 
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) 
                addToBot(new ApplyPowerAction(mo, p, new StrengthOnlyBuffPower(mo, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        } else {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) 
                addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        addToBot(new ApplyPowerAction(p, p, new DeadlyWagerPower(p, 2)));
    }

}
