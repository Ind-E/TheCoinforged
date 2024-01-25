package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.GamblePower;
import GamblerMod.util.CardStats;

public class Gamble extends BaseCard{
    private static final int MAGIC = 50;
    private static final int UPG_MAGIC = 10;

    public static final String ID = makeID(Gamble.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.NONE, 
            1 
    );

    public Gamble() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.loseGold(10);
        addToBot(new ApplyPowerAction(p, p, new GamblePower(p, this.magicNumber)));
    }

}