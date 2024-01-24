package GamblerMod.cards.tempCards;

public class RedSeven extends RedDieBase{
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedSeven.class.getSimpleName());

    public RedSeven() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

}
