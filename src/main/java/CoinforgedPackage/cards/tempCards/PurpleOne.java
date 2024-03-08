package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class PurpleOne extends PurpleDieBase {
    private static final int MAGIC = 1;

    public static final String ID = makeID(PurpleOne.class.getSimpleName());

    public PurpleOne() {
        super(ID, MAGIC);
    }
}
