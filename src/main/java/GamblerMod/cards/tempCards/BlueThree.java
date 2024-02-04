package GamblerMod.cards.tempCards;

public class BlueThree extends BlueDieBase{
    private static final int BLOCK = 3;

    public static final String ID = makeID(BlueThree.class.getSimpleName());

    public BlueThree() {
        super(ID, BLOCK);
    }

}
