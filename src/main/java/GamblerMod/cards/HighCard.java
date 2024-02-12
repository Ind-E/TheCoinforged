package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class HighCard extends BaseCard {
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 6;
    private static final int CARD_DRAW = 1;

    public static final String ID = makeID(HighCard.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public HighCard() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(CARD_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,
                new DamageInfo(p, this.damage, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL),
                AttackEffect.NONE));
        addToBot(new DrawCardAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HighCard();
    }
}