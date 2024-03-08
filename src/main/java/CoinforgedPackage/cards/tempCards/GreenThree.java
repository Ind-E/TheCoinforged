package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class GreenThree extends GreenDieBase {
    private static final int MAGIC = 3;

    public static final String ID = makeID(GreenThree.class.getSimpleName());

    public GreenThree() {
        super(ID, MAGIC);
    }
}
