package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;

public class CoinFlick extends AbstractCoinforgedCard {
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 4;

    public static final String ID = makeID(CoinFlick.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0);

    public CoinFlick() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        if (cardPlayed.costForTurn >= 2) {
            addToBot(new DiscardToHandAction(this));
            addToBot(new ModifyDamageAction(this.uuid, cardPlayed.costForTurn));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
}