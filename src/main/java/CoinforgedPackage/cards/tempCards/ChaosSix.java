package CoinforgedPackage.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DuplicationPower;

import CoinforgedPackage.cards.AbstractCoinforgedCard;
import CoinforgedPackage.cards.CustomTags;
import CoinforgedPackage.util.CardStats;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class ChaosSix extends AbstractCoinforgedCard {
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(ChaosSix.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0);

    public ChaosSix() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
        tags.add(CustomTags.MAGIC_DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DuplicationPower(p, this.magicNumber)));
    }

}
