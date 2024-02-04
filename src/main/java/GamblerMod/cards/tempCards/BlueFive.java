package GamblerMod.cards.tempCards;

public class BlueFive extends BlueDieBase{
    private static final int BLOCK = 5;

    public static final String ID = makeID(BlueFive.class.getSimpleName());

    public BlueFive() {
        super(ID, BLOCK);
    }

}
