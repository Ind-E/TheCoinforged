package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;
import basemod.ReflectionHacks;

public class GameChanger extends AbstractCoinforgedCard {

    public static final String ID = makeID(GameChanger.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            1);

    public GameChanger() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int multiplier;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {

            if (mo.intent == AbstractMonster.Intent.ATTACK || mo.intent == AbstractMonster.Intent.ATTACK_BUFF
                    || mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF
                    || mo.intent == AbstractMonster.Intent.ATTACK_DEFEND) {

                multiplier = 1;
                EnemyMoveInfo move = ReflectionHacks.getPrivate(mo, AbstractMonster.class, "move");
                if (move.isMultiDamage) {
                    multiplier = move.multiplier;
                }

                for (int i = 0; i < multiplier; i++) {
                    addToBot(new DamageAction(mo, new DamageInfo(p, mo.getIntentDmg(), DamageType.THORNS),
                            AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.exhaust = false;
        }
    }
}