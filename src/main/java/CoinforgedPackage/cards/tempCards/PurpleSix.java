package CoinforgedPackage.cards.tempCards;

public class PurpleSix extends PurpleDieBase {
    private static final int MAGIC = 6;

    public static final String ID = makeID(PurpleSix.class.getSimpleName());

    public PurpleSix() {
        super(ID, MAGIC);
    }

    public PurpleSix(int magic) {
        super(ID, magic);
    }
}
