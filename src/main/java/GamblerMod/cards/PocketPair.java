package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.actions.RollGreenAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;
import basemod.helpers.CardModifierManager;
import modifiers.RetainModifier;

public class PocketPair extends BaseCard{

    public static final String ID = makeID(PocketPair.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.NONE,
        1
    );

    public PocketPair() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        RollGreenAction green = new RollGreenAction(p, 1);
        AbstractCard c1 = green.roll(), c2 = green.roll();
        addToBot(new MakeTempCardInHandAction(c1, true, true));
        addToBot(new MakeTempCardInHandAction(c2, true, true));
        if (upgraded) {
            CardModifierManager.addModifier(c1, new RetainModifier());
            CardModifierManager.addModifier(c2, new RetainModifier());
        }
        
    }
}