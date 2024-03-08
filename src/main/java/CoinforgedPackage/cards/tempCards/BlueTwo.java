package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class BlueTwo extends BlueDieBase {
    private static final int BLOCK = 2;

    public static final String ID = makeID(BlueTwo.class.getSimpleName());

    public BlueTwo() {
        super(ID, BLOCK);
    }

}
