package CoinforgedPackage.cards.tempCards;

public class PurpleThree extends PurpleDieBase {
    private static final int MAGIC = 3;

    public static final String ID = makeID(PurpleThree.class.getSimpleName());

    public PurpleThree() {
        super(ID, MAGIC);
    }
}
