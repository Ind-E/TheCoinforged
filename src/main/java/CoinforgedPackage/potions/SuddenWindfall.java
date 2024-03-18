package CoinforgedPackage.potions;

import static CoinforgedPackage.CoinforgedMain.makeID;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.SacredBark;

public class SuddenWindfall extends BasePotion {
    public static final String ID = makeID("SuddenWindfall");

    private static AbstractPlayer p = AbstractDungeon.player;

    private static final Color LIQUID_COLOR = CardHelper.getColor(249, 209, 0);
    private static final Color HYBRID_COLOR = null;
    private static final Color SPOTS_COLOR = null;

    public SuddenWindfall() {
        super(ID, 0, PotionRarity.UNCOMMON, PotionSize.SPHERE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
    }

    private static int missingHealth() {
        p = AbstractDungeon.player;
        if (p == null) {
            return 0;
        } else {
            return p.maxHealth - p.currentHealth;
        }
    }

    @Override
    public String getDescription() {
        return AbstractDungeon.player.hasRelic(SacredBark.ID)
                ? potionStrings.DESCRIPTIONS[0] + missingHealth() * 4 + potionStrings.DESCRIPTIONS[1]
                : potionStrings.DESCRIPTIONS[0] + missingHealth() * 2 + potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractDungeon.player.gainGold(missingHealth() * 2);
    }

    @Override
    public boolean canUse() {
        if (AbstractDungeon.getCurrRoom().event != null && AbstractDungeon.getCurrRoom().event instanceof WeMeetAgain)
            return false;
        return true;

    }

    @Deprecated
    public void update() {
        super.update();
        this.description = getDescription();

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        addAdditionalTips();
    }
}
