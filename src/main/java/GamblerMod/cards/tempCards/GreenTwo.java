package GamblerMod.cards.tempCards;

public class GreenTwo extends BlueDieBase{
    private static final int MAGIC = 2;

    public static final String ID = makeID(GreenTwo.class.getSimpleName());

    public GreenTwo() {
        super(ID, MAGIC);
    }
}
