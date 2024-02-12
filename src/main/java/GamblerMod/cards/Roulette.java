package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class Roulette extends BaseCard {
    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;

    public static final String ID = makeID(ShuffleStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        2
    );

    public Roulette() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));

        AbstractCard randomStatusCard = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.STATUS).makeCopy();
        if (randomStatusCard != null) {
            addToBot(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(randomStatusCard));
        }
        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        if (c != null) {
            c.setCostForTurn(this.upgraded ? 0 : c.cost - 1);
            addToBot(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(c));
        }
    }
}
