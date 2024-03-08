package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class PurpleFour extends PurpleDieBase {
    private static final int MAGIC = 4;

    public static final String ID = makeID(PurpleFour.class.getSimpleName());

    public PurpleFour() {
        super(ID, MAGIC);
    }
}
