package GamblerMod.cards.tempCards;

public class RedNine extends RedDieBase{
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedNine.class.getSimpleName());

    public RedNine() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

}
