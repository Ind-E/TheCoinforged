package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class BlueThree extends BlueDieBase {
    private static final int BLOCK = 3;

    public static final String ID = makeID(BlueThree.class.getSimpleName());

    public BlueThree() {
        super(ID, BLOCK);
    }

}
