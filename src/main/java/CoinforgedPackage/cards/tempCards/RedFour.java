package CoinforgedPackage.cards.tempCards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

@NoCompendium
public class RedFour extends RedDieBase {
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedFour.class.getSimpleName());

    public RedFour() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

    public RedFour(boolean redKingActive) {
        super(ID, info_all, DAMAGE, UPG_DAMAGE, redKingActive);
    }

}
