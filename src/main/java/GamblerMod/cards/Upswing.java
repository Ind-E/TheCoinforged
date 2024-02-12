package GamblerMod.cards;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import GamblerMod.GamblerMod;
import GamblerMod.actions.RollBlueAction;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class Upswing extends BaseCard {
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int DICE_TO_ROLL = 1;
    private static final int UPG_DICE_TO_ROLL = 1;

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(Upswing.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Gambler.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1);

    public Upswing() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DICE_TO_ROLL, UPG_DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,
                new DamageInfo(p, this.damage, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL),
                AttackEffect.NONE));
        addToBot(new RollBlueAction(p, this.magicNumber));
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

    public AbstractCard makeCopy() {
        return new Upswing();
    }
}