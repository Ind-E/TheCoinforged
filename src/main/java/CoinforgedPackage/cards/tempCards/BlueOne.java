package CoinforgedPackage.cards.tempCards;

public class BlueOne extends BlueDieBase {
    private static final int BLOCK = 1;

    public static final String ID = makeID(BlueOne.class.getSimpleName());

    public BlueOne() {
        super(ID, BLOCK);
    }
}
