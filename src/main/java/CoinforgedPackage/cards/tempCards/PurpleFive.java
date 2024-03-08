package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class PurpleFive extends PurpleDieBase {
    private static final int MAGIC = 5;

    public static final String ID = makeID(PurpleFive.class.getSimpleName());

    public PurpleFive() {
        super(ID, MAGIC);
    }
}
