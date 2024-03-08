package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class RedFive extends RedDieBase {
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedFive.class.getSimpleName());

    public RedFive() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

    public RedFive(boolean redKingActive) {
        super(ID, info_all, DAMAGE, UPG_DAMAGE, redKingActive);
    }
}
