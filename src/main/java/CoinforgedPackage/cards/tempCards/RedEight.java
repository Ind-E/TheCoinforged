package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class RedEight extends RedDieBase {
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedEight.class.getSimpleName());

    public RedEight() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

    public RedEight(boolean redKingActive) {
        super(ID, info_all, DAMAGE, UPG_DAMAGE, redKingActive);
    }

}
