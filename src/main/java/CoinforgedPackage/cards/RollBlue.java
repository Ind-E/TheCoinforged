package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class RollBlue extends AbstractMultiPreviewCard {
    private static final int DICE_TO_ROLL = 1;
    private static final int UPG_DICE_TO_ROLL = 1;

    public static final String ID = makeID(RollBlue.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            0);

    public RollBlue() {
        super(ID, info);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollBlueAction(p, this.magicNumber));
    }
    
    public ArrayList<CardTags> getTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.BLUE_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new RollBlue();
    }
}
