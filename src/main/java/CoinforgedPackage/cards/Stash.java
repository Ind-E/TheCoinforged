package CoinforgedPackage.cards;

import java.util.HashMap;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import CoinforgedPackage.util.CardStats;
import CoinforgedPackage.character.Coinforged;

public class Stash extends AbstractRandomChipCard{
    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public static final String ID = makeID(Stash.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Coinforged.Enums.CARD_COLOR, 
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        1
    );

    public Stash() {
        super(ID, info, initializeChipWeights(), false);
        setBlock(BLOCK, UPG_BLOCK);
    }

    public Stash(ChipColor chip) {
        super(ID, info, chip, initializeChipWeights(), false);
        setBlock(BLOCK, UPG_BLOCK);
    }

    private static HashMap<ChipColor, Integer> initializeChipWeights() {
        HashMap<ChipColor, Integer> chipWeights = new HashMap<>();
        chipWeights.put(ChipColor.Black, 1);
        chipWeights.put(ChipColor.Blue, 0);
        chipWeights.put(ChipColor.Cracked, 0);
        chipWeights.put(ChipColor.Gray, 0);
        chipWeights.put(ChipColor.Green, 0);
        chipWeights.put(ChipColor.Orange, 0);
        chipWeights.put(ChipColor.Red, 0);
        chipWeights.put(ChipColor.White, 0);
        return chipWeights;
    }

    @Override
    protected HashMap<ChipColor, Integer> getChipWeights() {
        HashMap<ChipColor, Integer> weights = new HashMap<>();
        weights.put(ChipColor.Black, 1);
        weights.put(ChipColor.Blue, 0);
        weights.put(ChipColor.Cracked, 0);
        weights.put(ChipColor.Gray, 0);
        weights.put(ChipColor.Green, 0);
        weights.put(ChipColor.Orange, 0);
        weights.put(ChipColor.Red, 0);
        weights.put(ChipColor.White, 0);
        return weights;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return this.chip == null ? new Stash() : new Stash(this.chip);
    }
}