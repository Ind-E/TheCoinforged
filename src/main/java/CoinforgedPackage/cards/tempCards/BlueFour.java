package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class BlueFour extends BlueDieBase {
    private static final int BLOCK = 4;

    public static final String ID = makeID(BlueFour.class.getSimpleName());

    public BlueFour() {
        super(ID, BLOCK);
    }

}
