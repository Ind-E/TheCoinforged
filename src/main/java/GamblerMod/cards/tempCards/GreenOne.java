package GamblerMod.cards.tempCards;

public class GreenOne extends BlueDieBase{
    private static final int MAGIC = 1;

    public static final String ID = makeID(GreenOne.class.getSimpleName());

    public GreenOne() {
        super(ID, MAGIC);
    }
}
