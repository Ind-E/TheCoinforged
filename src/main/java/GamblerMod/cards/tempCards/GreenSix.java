package GamblerMod.cards.tempCards;

public class GreenSix extends BlueDieBase{
    private static final int MAGIC = 6;

    public static final String ID = makeID(GreenSix.class.getSimpleName());

    public GreenSix() {
        super(ID, MAGIC);
    }

    public GreenSix(int magic) {
        super(ID, magic);
    }
}
