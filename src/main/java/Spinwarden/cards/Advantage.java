package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.SpinwardenMain;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.powers.AdvantagePower;
import Spinwarden.util.CardStats;

public class Advantage extends BaseCard {
    private static final int MAGIC = 1;

    public static final String ID = makeID(Advantage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0);

    public Advantage() {
        super(ID, info);
        setMagic(MAGIC);
    }

    public void upgrade() {
        this.selfRetain = true;
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int diceInHand = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(SpinwardenMain.DIE)) {
                diceInHand++;
            }
        }
        if (diceInHand > 0)
            addToBot(new ApplyPowerAction(p, p, new AdvantagePower(p, diceInHand)));
    }

    public AbstractCard makeCopy() {
        return new Advantage();
    }

}
