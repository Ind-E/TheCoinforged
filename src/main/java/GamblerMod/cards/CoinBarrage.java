package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.ModifiedHandSizePower;
import GamblerMod.util.CardStats;

public class CoinBarrage extends BaseCard {
    private static final int DAMAGE = 1;
    private static final int HAND_SIZE_REDUCTION = 2;
    private static final int UPG_HAND_SIZE_REDUCTION = -1;
    private static final int TIMES_TO_DEAL_DAMAGE = 11;

    public static final String ID = makeID(CoinBarrage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1);

    public CoinBarrage() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(HAND_SIZE_REDUCTION, UPG_HAND_SIZE_REDUCTION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < TIMES_TO_DEAL_DAMAGE; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.NONE));
        }
        addToBot(new ApplyPowerAction(p, p, new ModifiedHandSizePower(p, -this.magicNumber), -this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CoinBarrage();
    }

}
