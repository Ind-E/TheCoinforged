package GamblerMod.cards.tempCards;

public class RedTwo extends RedDieBase{
    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(RedTwo.class.getSimpleName());

    public RedTwo() {
        super(ID, DAMAGE, UPG_DAMAGE);
    }

}
