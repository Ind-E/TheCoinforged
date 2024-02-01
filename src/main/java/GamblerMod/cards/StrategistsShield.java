package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class StrategistsShield extends BaseCard{
    private static final int BLOCK = 6;

    public static final String ID = makeID(StrategistsShield.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL, 
        CardRarity.UNCOMMON, 
        CardTarget.SELF, 
        1 
    );

    public StrategistsShield() {
        super(ID, info);
        setBlock(BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        int powers = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.POWER || upgraded && c.type == AbstractCard.CardType.STATUS) {
                powers++;
            } 
        }
        if (powers > 0)
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, powers)));
    }

}
