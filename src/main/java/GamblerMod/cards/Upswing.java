package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.RollBlueAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class Upswing extends BaseCard{
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int DICE_TO_ROLL = 1;
    private static final int UPG_DICE_TO_ROLL = 1;

    public static final String ID = makeID(Upswing.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        1
    );

    public Upswing() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL), AttackEffect.NONE));
        addToBot(new RollBlueAction(p, this.magicNumber));
    }
}