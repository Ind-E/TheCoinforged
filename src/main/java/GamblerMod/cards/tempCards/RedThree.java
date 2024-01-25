package GamblerMod.cards.tempCards;

public class RedThree extends RedDieBase{
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedThree.class.getSimpleName());

    public RedThree() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

    public RedThree(boolean redKingActive) {
        super(ID, info_all, DAMAGE, UPG_DAMAGE, redKingActive);
    }
}
