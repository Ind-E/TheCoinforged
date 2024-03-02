package CoinforgedPackage.cards;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.CoinforgedMod;
import CoinforgedPackage.actions.RollGreenAction;
import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.modifiers.RetainModifier;
import CoinforgedPackage.powers.LoadedDicePower;
import CoinforgedPackage.util.CardStats;
import basemod.helpers.CardModifierManager;

public class PocketPair extends BaseCard {
    private static final int DICE_TO_ROLL = 2;

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    public static final String ID = makeID(PocketPair.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1);

    public PocketPair() {
        super(ID, info);
        this.exhaust = true;
        setMagic(DICE_TO_ROLL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        RollGreenAction green = new RollGreenAction(p, 1);
        AbstractCard c1 = green.roll(), c2 = green.roll();
        if (p.hasPower(LoadedDice.ID)) {
            int amt = p.getPower(LoadedDicePower.POWER_ID).amount;
            c1.baseMagicNumber += amt;
            c2.baseMagicNumber += amt;
            c1.magicNumber = c1.baseMagicNumber;
            c2.magicNumber = c2.baseMagicNumber;
            c1.initializeDescription();
            c2.initializeDescription();
        }
        addToBot(new MakeTempCardInHandAction(c1, true, true));
        addToBot(new MakeTempCardInHandAction(c2, true, true));
        if (upgraded) {
            CardModifierManager.addModifier(c1, new RetainModifier());
            CardModifierManager.addModifier(c2, new RetainModifier());
        }
    }

    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> myList = new ArrayList<>();
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q.hasTag(CoinforgedMod.GREEN_DIE) && q.damage <= 6) {
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

    @Override
    public AbstractCard makeCopy() {
        return new PocketPair();
    }
}