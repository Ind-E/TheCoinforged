package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class GreenFive extends GreenDieBase {
    private static final int MAGIC = 5;

    public static final String ID = makeID(GreenFive.class.getSimpleName());

    public GreenFive() {
        super(ID, MAGIC);
    }
}
