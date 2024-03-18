package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.DrawbackPower;

public class CoinFlick extends BaseCard{
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 1;

    public static final String ID = makeID(CoinFlick.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        0
    );

    public CoinFlick() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
        setMagic(MAGIC);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m, p, new DrawbackPower(m, this.magicNumber), this.magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(this, 1, false, true));
    }
}