package CoinforgedPackage.actions;

import static CoinforgedPackage.util.GeneralUtils.getNumChips;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.Settings;

public class BuyInAction extends AbstractGameAction {

    public BuyInAction() {
        super();
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        addToBot(new GainEnergyAction(getNumChips()));
        this.isDone = true;
    }
}