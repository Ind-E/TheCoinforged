package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.AdvantagePower;
import GamblerMod.util.CardStats;
import GamblerMod.GamblerMod;

public class Advantage extends BaseCard{
    private static final int MAGIC = 1;

    public static final String ID = makeID(Advantage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.NONE, 
            0 
    );

    public Advantage() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MAGIC);
    }

    public void upgrade() {
        this.retain = true;
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int diceInHand = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(GamblerMod.DIE)) {
                ++diceInHand;
            } 
        }
        if (diceInHand > 0)
            addToBot(new ApplyPowerAction(p, p, new AdvantagePower(p, diceInHand)));
    }

}
