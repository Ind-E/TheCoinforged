package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.actions.GrapesAction;
import CoinforgedPackage.character.Coinforged;

public class Grapes extends AbstractCoinforgedCard{
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(Grapes.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        0
    );

    public Grapes() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        setEthereal(true);
        setExhaust(true);
    }

    public Grapes(int cost, boolean upgraded) {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.baseCost = this.cost = cost;
        this.costForTurn = this.cost;
        if (upgraded) {
            this.upgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GrapesAction(this.magicNumber));
        addToBot(new MakeTempCardInHandAction(new Grapes(this.cost + 1, this.upgraded)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Grapes();
    }
}