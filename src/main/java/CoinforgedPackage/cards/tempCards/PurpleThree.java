package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class PurpleThree extends PurpleDieBase {
    private static final int MAGIC = 3;

    public static final String ID = makeID(PurpleThree.class.getSimpleName());

    public PurpleThree() {
        super(ID, MAGIC);
    }
}
