package GamblerMod.cards.tempCards;

public class GreenOne extends GreenDieBase{
    private static final int MAGIC = 1;

    public static final String ID = makeID(GreenOne.class.getSimpleName());

    public GreenOne() {
        super(ID, MAGIC);
    }
}
