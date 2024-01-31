package GamblerMod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.DoubleDealingAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

// BUG: If switched card has retain (or upgrades to have retain), it isn't retained after switching cost, even on future turns
public class DoubleDealing extends BaseCard{

    public static final String ID = makeID(DoubleDealing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.NONE, 
            1 
    );

    public DoubleDealing() {
        super(ID, info);
        this.exhaust = true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            upgradeBaseCost(0);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoubleDealingAction());
    }

}
