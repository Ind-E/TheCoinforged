package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import Spinwarden.actions.IfChipsAction;
import Spinwarden.actions.MakeChipsInHandAction;
import Spinwarden.cards.tempCards.PokerChip;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class GlassShield extends BaseCard {
    private static final int CHIPS = 1;
    private static final int MAGIC = 1;

    public static final String ID = makeID(GlassShield.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1);

    public GlassShield() {
        super(ID, info);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IfChipsAction(CHIPS, new ApplyPowerAction(p, p, new BufferPower(p, this.magicNumber))));
        if (upgraded) {
            addToBot(new MakeChipsInHandAction(1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GlassShield();
    }
}