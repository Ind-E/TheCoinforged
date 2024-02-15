package Spinwarden.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.actions.CycleStrikeAction;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class CycleStrike extends BaseCard {
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private static final int X_COST = -1;

    public static final String ID = makeID(CycleStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            X_COST);

    public CycleStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.isMultiDamage = true;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CycleStrikeAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce,
                this.energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new CycleStrike();
    }
}