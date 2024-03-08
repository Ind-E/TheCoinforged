package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class RedSeven extends RedDieBase {
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedSeven.class.getSimpleName());

    public RedSeven() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

    public RedSeven(boolean redKingActive) {
        super(ID, info_all, DAMAGE, UPG_DAMAGE, redKingActive);
    }

}
