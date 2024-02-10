package GamblerMod.cards;

import java.util.concurrent.ThreadLocalRandom;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class CheatersLuck extends BaseCard{
    private static final int CARDS_TO_DUPE = 1;
    private static final int UPG_CARDS_TO_DUPE = 1;

    public static final String ID = makeID(CheatersLuck.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.NONE,
        1
    );

    public CheatersLuck() {
        super(ID, info);
        setMagic(CARDS_TO_DUPE, UPG_CARDS_TO_DUPE);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.size() <= 1)
            return;

        AbstractCard randomCardInHand = p.hand.group.get(ThreadLocalRandom.current().nextInt(0, p.hand.size())).makeCopy();
        while (randomCardInHand.originalName == this.originalName) {
            randomCardInHand = p.hand.group.get(ThreadLocalRandom.current().nextInt(0, p.hand.size())).makeCopy();
        }
        randomCardInHand.setCostForTurn(0);
        for (int i = 0; i < this.magicNumber; i++)
            addToBot(new MakeTempCardInHandAction(randomCardInHand));
    }
}