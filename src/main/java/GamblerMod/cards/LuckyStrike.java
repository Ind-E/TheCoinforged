package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

import java.util.concurrent.ThreadLocalRandom;

public class LuckyStrike extends BaseCard{

    public static final String ID = makeID(LuckyStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.BASIC, 
            CardTarget.ENEMY, 
            1 
    );

    public LuckyStrike() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int dmg = ThreadLocalRandom.current().nextInt(1, 6 + 1) + ThreadLocalRandom.current().nextInt(1, 6 + 1);
        if (this.upgraded) {
            dmg += 3;
        } 
        addToBot(new DamageAction(m, new DamageInfo(p, dmg, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

}
