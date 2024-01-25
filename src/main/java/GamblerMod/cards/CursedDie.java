package GamblerMod.cards;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.scenes.scene2d.actions.RemoveAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;
import basemod.BaseMod;

public class CursedDie extends BaseCard{

    public static final String ID = makeID(CursedDie.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.CURSE, 
            CardRarity.SPECIAL, 
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
        AbstractDungeon.player.masterDeck.removeCard(CursedDie.ID);
        if (num == 6) {
            
        }

    }

}