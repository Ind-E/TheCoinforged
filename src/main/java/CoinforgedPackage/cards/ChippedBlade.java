package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.MakeChipsInHandAction;
import CoinforgedPackage.cards.chips.WhiteChip;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.powers.CripplingDebtPower;

public class ChippedBlade extends BaseCard {
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;
    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 2;

    public static final String ID = makeID(ChippedBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            2);

    public ChippedBlade() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        this.cardsToPreview = new WhiteChip();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m, p, new CripplingDebtPower(m, this.magicNumber)));
        // addToBot(new ChippedBladeAction(m));
        addToBot(new MakeChipsInHandAction(1));
    }
}