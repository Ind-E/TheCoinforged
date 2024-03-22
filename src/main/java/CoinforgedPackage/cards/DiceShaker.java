package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import CoinforgedPackage.actions.RollRedAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class DiceShaker extends AbstractCoinforgedCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(DiceShaker.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public DiceShaker() {
        super(ID, info, true);
        setMagic(MAGIC, UPG_MAGIC);
    }

    public ArrayList<CardTags> getTags() {
        ArrayList<CardTags> tags = new ArrayList<>();
        tags.add(CustomTags.RED_DIE);
        return tags;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollRedAction(p, 1));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false)));
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 1), 1));
    }
}