package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

//TODO: add orb or power to return card to hand after x turns
public class LongGame extends BaseCard {
    private static final int DAMAGE = 35;
    private static final int UPG_DAMAGE = 5;
    private static final int TURNS_UNTIL_PLAYABLE = 5;
    private static final int UPG_TURNS_UNTIL_PLAYABLE = -1;
    private static final int STRENGTH = 3;

    public static final String ID = makeID(LongGame.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2);

    public LongGame() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(TURNS_UNTIL_PLAYABLE, UPG_TURNS_UNTIL_PLAYABLE);
        this.isMultiDamage = true;
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, STRENGTH)));
    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new LongGame();
    }
}