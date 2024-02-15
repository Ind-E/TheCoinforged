package Spinwarden.cards.tempCards;

public class GreenFive extends GreenDieBase {
    private static final int MAGIC = 5;

    public static final String ID = makeID(GreenFive.class.getSimpleName());

    public GreenFive() {
        super(ID, MAGIC);
    }
}
