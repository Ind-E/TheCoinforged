package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.modifiers.ExhaustModifier;
import CoinforgedPackage.util.CardStats;
import static CoinforgedPackage.util.GeneralUtils.generateRandomStatusCard;
import basemod.helpers.CardModifierManager;

//TODO: rework or remove
public class Roulette extends BaseCard {
    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;

    public static final String ID = makeID(Roulette.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2);

    public Roulette() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));

        AbstractCard randomStatusCard = generateRandomStatusCard();
        if (randomStatusCard != null) {
            addToBot(new com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction(randomStatusCard, 1));
        }

        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        if (!upgraded) {
            while (c.cost > 3) {
                c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            }
        }

        if (c != null) {
            CardModifierManager.addModifier(c, new ExhaustModifier());
            int costForTurn = this.upgraded ? 0 : Math.max(0, c.cost - 1);
            c.setCostForTurn(costForTurn);
            addToBot(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(c));
        }
    }

    public AbstractCard makeCopy() {
        return new Roulette();
    }
}
