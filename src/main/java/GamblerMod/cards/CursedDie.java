package GamblerMod.cards;

import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class CursedDie extends BaseCard{

    public static final String ID = makeID(CursedDie.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.CURSE, 
            CardRarity.CURSE, 
            CardTarget.NONE, 
            1 
    );

    public CursedDie() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int num = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        if (num == 6) {
            AbstractDungeon.player.masterDeck.removeCard(CursedDie.ID);
        }

    }

}
