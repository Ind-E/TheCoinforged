package Spinwarden.cards.tempCards;

public class RedOne extends RedDieBase {
    private static final int DAMAGE = 1;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedOne.class.getSimpleName());

    public RedOne() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

    public RedOne(boolean redKingActive) {
        super(ID, info_all, DAMAGE, UPG_DAMAGE, redKingActive);
    }
}
