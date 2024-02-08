package GamblerMod.cards.tempCards;

public class GreenFour extends GreenDieBase{
    private static final int MAGIC = 4;

    public static final String ID = makeID(GreenFour.class.getSimpleName());

    public GreenFour() {
        super(ID, MAGIC);
    }
}
