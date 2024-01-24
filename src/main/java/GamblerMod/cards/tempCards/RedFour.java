package GamblerMod.cards.tempCards;

public class RedFour extends RedDieBase{
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedFour.class.getSimpleName());

    public RedFour() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

}
