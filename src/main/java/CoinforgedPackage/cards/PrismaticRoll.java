package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.actions.RollBlueAction;
import CoinforgedPackage.actions.RollGreenAction;
import CoinforgedPackage.actions.RollPurpleAction;
import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class PrismaticRoll extends AbstractMultiPreviewCard {
    private static final int DICE_TO_ROLL = 1;

    public static final String ID = makeID(PrismaticRoll.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2);

    public PrismaticRoll() {
        super(ID, info);
        setMagic(DICE_TO_ROLL);
        setExhaust(true, false);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollRedAction(p, this.magicNumber));
        addToBot(new RollBlueAction(p, this.magicNumber));
        addToBot(new RollGreenAction(p, this.magicNumber));
        addToBot(new RollPurpleAction(p, this.magicNumber));
    }

    public ArrayList<CardTags> getTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        tags.add(CustomTags.BLUE_DIE);
        tags.add(CustomTags.GREEN_DIE);
        tags.add(CustomTags.PURPLE_DIE);
        return tags;
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrismaticRoll();
    }
}