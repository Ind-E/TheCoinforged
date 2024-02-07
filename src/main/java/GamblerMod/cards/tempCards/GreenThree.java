package GamblerMod.cards.tempCards;

public class GreenThree extends BlueDieBase{
    private static final int MAGIC = 3;

    public static final String ID = makeID(GreenThree.class.getSimpleName());

    public GreenThree() {
        super(ID, MAGIC);
    }
}
