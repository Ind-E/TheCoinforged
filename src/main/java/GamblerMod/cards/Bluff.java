package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class Bluff extends BaseCard{
    private static final int DAMAGE = 16;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;

    public static final String ID = makeID(Bluff.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK, 
        CardRarity.UNCOMMON, 
        CardTarget.ENEMY, 
        2 
    );

    public Bluff() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        int debuffCount = countDebuffs();
        
        if (!upgraded && debuffCount == 0 || upgraded && debuffCount <= 1) {
            addToBot(new GainBlockAction(p, this.block));
        }
    }

    private int countDebuffs() {
        int debuffCount = 0;
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                debuffCount++;
            } 
        }
        return debuffCount;
    }

    @Override
    public void triggerOnGlowCheck() {
        int debuffCount = countDebuffs();
        if (!upgraded && debuffCount == 0 || upgraded && debuffCount <= 1) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        } 
    }
}
