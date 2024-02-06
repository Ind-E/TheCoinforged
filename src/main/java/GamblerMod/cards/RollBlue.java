package GamblerMod.cards;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.actions.RollBlueAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;
import GamblerMod.GamblerMod;

public class RollBlue extends BaseCard{
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int minroll = 1;
    private static final int maxroll = 6;

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(RollBlue.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.BASIC, 
            CardTarget.NONE, 
            1 
    );

    public RollBlue() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollBlueAction(p, this.magicNumber, minroll, maxroll));
    }
    
    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> myList = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q.hasTag(GamblerMod.BLUE_DIE) && q.block <= 6) {
                AbstractCard r = q.makeCopy();
                myList.add(r);
            } 
        } 
        return myList;
    }

    public void update() {
        super.update();
        if (this.dupeListForPrev.isEmpty())
            this.dupeListForPrev.addAll(getList()); 
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 1.5F;
                if (this.dupeListForPrev.size() == 0) {
                this.cardsToPreview = (AbstractCard)CardLibrary.cards.get("Madness");
                } else {
                this.cardsToPreview = this.dupeListForPrev.get(this.previewIndex);
                } 
                if (this.previewIndex == this.dupeListForPrev.size() - 1) {
                this.previewIndex = 0;
                } else {
                this.previewIndex++;
                } 
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }  
  }
}