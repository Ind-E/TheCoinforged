package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

import java.util.concurrent.ThreadLocalRandom;

public class LuckyDefend extends BaseCard{

    public static final String ID = makeID(LuckyDefend.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.BASIC, 
            CardTarget.NONE, 
            0 
    );

    public LuckyDefend() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int block = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        if (this.upgraded) {
            block += 3;
        } 
        addToBot(new GainBlockAction(p, block));
    }

}
