package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class GreenTwo extends GreenDieBase {
    private static final int MAGIC = 2;

    public static final String ID = makeID(GreenTwo.class.getSimpleName());

    public GreenTwo() {
        super(ID, MAGIC);
    }
}
