package CoinforgedPackage.powers;

import static CoinforgedPackage.CoinforgedMain.powerPath;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import CoinforgedPackage.util.TextureLoader;
import basemod.BaseMod;

public class ModifiedHandSizePower extends BasePower {
    public static final String POWER_ID = makeID(ModifiedHandSizePower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private static final Texture pos_48 = TextureLoader.getTexture(powerPath("ModifiedHandSizePower.png"));
    private static final Texture pos_128 = TextureLoader.getTexture(powerPath("large/ModifiedHandSizePower.png"));
    private static final Texture neg_48 = TextureLoader.getTexture(powerPath("ModifiedHandSizePowerNegative.png"));
    private static final Texture neg_128 = TextureLoader.getTexture(powerPath("large/ModifiedHandSizePowerNegative.png"));
    private int hand_size;

    public ModifiedHandSizePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, Math.min(Math.max(amount, -9), 5));
        this.canGoNegative = true;
        this.hand_size = BaseMod.MAX_HAND_SIZE;
        updateImage();
    }

    public void updateDescription() {
        if (this.amount < 0) {
            this.description = DESCRIPTIONS[0] + -this.amount + DESCRIPTIONS[1];
            this.type = AbstractPower.PowerType.DEBUFF;
        } else if (this.amount > 0) {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
            this.type = AbstractPower.PowerType.BUFF;
        }
    }

    @Override
    public void onInitialApplication() {
        this.hand_size = BaseMod.MAX_HAND_SIZE;
        updateImageAndHandSize();
    }

    @Override
    public void stackPower(int stackAmount) {
        if (this.amount + stackAmount > 5) {
            this.amount = 5;
        } else if (this.amount + stackAmount < -9) {
            this.amount = -9;
        } else {
            this.amount += stackAmount;
        }
        if (this.amount == 0)  {
            BaseMod.MAX_HAND_SIZE = this.hand_size;
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, ModifiedHandSizePower.POWER_ID));
        }
        updateImageAndHandSize();
    }

    private void updateImage() {
        if (this.amount < 0) {
            this.region48 = new TextureAtlas.AtlasRegion(neg_48, 0, 0, neg_48.getWidth(), neg_48.getHeight());
            this.region128 = new TextureAtlas.AtlasRegion(neg_128, 0, 0, neg_128.getWidth(), neg_128.getHeight());
        } else if (this.amount > 0) {
            this.region48 = new TextureAtlas.AtlasRegion(pos_48, 0, 0, pos_48.getWidth(), pos_48.getHeight());
            this.region128 = new TextureAtlas.AtlasRegion(pos_128, 0, 0, pos_128.getWidth(), pos_128.getHeight());
        }
    }

    private void updateImageAndHandSize() {
        if (this.amount < 0) {
            BaseMod.MAX_HAND_SIZE = Math.max(this.hand_size + this.amount, 1);
            this.region48 = new TextureAtlas.AtlasRegion(neg_48, 0, 0, neg_48.getWidth(), neg_48.getHeight());
            this.region128 = new TextureAtlas.AtlasRegion(neg_128, 0, 0, neg_128.getWidth(), neg_128.getHeight());
        } else if (this.amount > 0) {
            BaseMod.MAX_HAND_SIZE = Math.min(this.hand_size + this.amount, 15);
            this.region48 = new TextureAtlas.AtlasRegion(pos_48, 0, 0, pos_48.getWidth(), pos_48.getHeight());
            this.region128 = new TextureAtlas.AtlasRegion(pos_128, 0, 0, pos_128.getWidth(), pos_128.getHeight());
        }
    }

    @Override
    public void onRemove() {
        BaseMod.MAX_HAND_SIZE = this.hand_size;
    }

    @Override
    public void onVictory() {
        BaseMod.MAX_HAND_SIZE = this.hand_size;
    }
}