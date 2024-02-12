package GamblerMod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class SmallBlind extends BaseCard {
    private static final int BLOCK = 13;
    private static final int UPG_BLOCK = 4;
    private static final int GOLD_LOSS = 5;

    public static final String ID = makeID(SmallBlind.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1);

    public SmallBlind() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(GOLD_LOSS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        AbstractDungeon.player.loseGold(this.magicNumber);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SmallBlind();
    }

}
