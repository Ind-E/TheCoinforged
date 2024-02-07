package GamblerMod.cards.tempCards;

public class PurpleOne extends PurpleDieBase{
    private static final int MAGIC = 1;

    public static final String ID = makeID(PurpleOne.class.getSimpleName());

    public PurpleOne() {
        super(ID, MAGIC);
    }
}
