package GamblerMod.cards;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.powers.ChaosFormPower;
import GamblerMod.util.CardStats;
import GamblerMod.GamblerMod;

public class ChaosForm extends BaseCard{
    private static final int MAGIC = 1;

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(ChaosForm.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            3 
    );

    public ChaosForm() {
        super(ID, info);
        setMagic(MAGIC);
    }

    public void upgrade() {
        if (!this.upgraded) {
            super.upgrade();
            upgradeBaseCost(2);
        } 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ChaosFormPower(p)));
    }

    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> myList = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q.hasTag(GamblerMod.MAGIC_DIE) && q.damage <= 6) {
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
                this.rotationTimer = 2F;
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
