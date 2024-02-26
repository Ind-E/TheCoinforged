package Spinwarden.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.util.CardStats;
import Spinwarden.actions.IfChipsAction;
import Spinwarden.character.SpinwardenCharacter;

public class DoubleCherry extends BaseCard {
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;
    private static final int CHIPS = 2;

    public static final String ID = makeID(DoubleCherry.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1);

    public DoubleCherry() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new IfChipsAction(CHIPS, new GainBlockAction(p, this.block)));
    }
}