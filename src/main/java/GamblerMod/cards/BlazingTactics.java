package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import GamblerMod.actions.BlazingTacticsAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class BlazingTactics extends BaseCard{
    private static final int CARD_DRAW = 3;
    private static final int UPG_CARD_DRAW = 1;

    public static final String ID = makeID(BlazingTactics.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL, 
        CardRarity.UNCOMMON, 
        CardTarget.NONE, 
        0 
    );

    public BlazingTactics() {
        super(ID, info);
        setMagic(CARD_DRAW, UPG_CARD_DRAW);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(1));
        addToBot(new BlazingTacticsAction(this, this.magicNumber));
    }

    public void triggerOnGlowCheck() {
        if (EnergyPanel.totalCount + 1 > AbstractDungeon.player.hand.size() - 1) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        } 
    }
}
