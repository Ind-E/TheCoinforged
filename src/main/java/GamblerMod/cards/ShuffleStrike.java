package GamblerMod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.ShuffleStrikeAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class ShuffleStrike extends BaseCard{
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private static final int X_COST = -1;

    public static final String ID = makeID(ShuffleStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.UNCOMMON,
        CardTarget.ALL_ENEMY,
        X_COST
    );

    public ShuffleStrike() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.isMultiDamage = true;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ShuffleStrikeAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new ShuffleStrike();
    }
}