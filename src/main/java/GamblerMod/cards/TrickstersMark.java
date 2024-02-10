package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
        CardRarity.UNCOMMON, 
        CardTarget.ALL_ENEMY, 
        1 
    );

    public TrickstersMark() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature r = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
        if (r != null) {
            addToBot(new ApplyPowerAction(r, p, new VulnerablePower(p, this.magicNumber, false)));
            addToBot(new ApplyPowerAction(r, p, new WeakPower(p, this.magicNumber, false)));
        }
    }

}
