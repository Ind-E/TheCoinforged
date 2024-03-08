package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class GreenOne extends GreenDieBase {
    private static final int MAGIC = 1;

    public static final String ID = makeID(GreenOne.class.getSimpleName());

    public GreenOne() {
        super(ID, MAGIC);
    }
}
