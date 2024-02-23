package Spinwarden.cards;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Spinwarden.SpinwardenMain;
import Spinwarden.actions.RollBlueAction;
import Spinwarden.actions.RollGreenAction;
import Spinwarden.actions.RollPurpleAction;
import Spinwarden.actions.RollRedAction;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.util.CardStats;

public class PrismaticRoll extends BaseCard {
    private static final int DICE_TO_ROLL = 1;

    public float rotationTimer;
    public int previewIndex;
    public ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(PrismaticRoll.class.getSimpleName());
    private static final CardStats info = new CardStats(
            SpinwardenCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            2);

    public PrismaticRoll() {
        super(ID, info);
        setMagic(DICE_TO_ROLL);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            this.exhaust = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RollRedAction(p, this.magicNumber));
        addToBot(new RollBlueAction(p, this.magicNumber));
        addToBot(new RollGreenAction(p, this.magicNumber));
        addToBot(new RollPurpleAction(p, this.magicNumber));
    }

    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> myList = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q.hasTag(SpinwardenMain.RED_DIE) && q.damage <= 6) {
                AbstractCard r = q.makeCopy();
                myList.add(r);
            } else if (q.hasTag(SpinwardenMain.BLUE_DIE) && q.block <= 6) {
                AbstractCard r = q.makeCopy();
                myList.add(r);
            } else if (q.hasTag(SpinwardenMain.GREEN_DIE) && q.magicNumber <= 6) {
                AbstractCard r = q.makeCopy();
                myList.add(r);
            } else if (q.hasTag(SpinwardenMain.PURPLE_DIE) && q.magicNumber <= 6) {
                AbstractCard r = q.makeCopy();
                myList.add(r);
            }
        }
        Collections.shuffle(myList);
        return myList;
    }

    @Override
    public void update() {
        super.update();
        if (this.dupeListForPrev.isEmpty())
            this.dupeListForPrev.addAll(getList());
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 1.5F;
                if (this.dupeListForPrev.size() == 0) {
                    this.cardsToPreview = (AbstractCard) CardLibrary.cards.get("Madness");
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

    @Override
    public AbstractCard makeCopy() {
        return new PrismaticRoll();
    }
}