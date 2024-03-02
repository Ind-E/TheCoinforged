package CoinforgedPackage.cards.tempCards;

public class PurpleTwo extends PurpleDieBase {
    private static final int MAGIC = 2;

    public static final String ID = makeID(PurpleTwo.class.getSimpleName());

    public PurpleTwo() {
        super(ID, MAGIC);
    }
}
