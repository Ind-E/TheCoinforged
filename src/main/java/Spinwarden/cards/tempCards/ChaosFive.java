package Spinwarden.cards.tempCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import Spinwarden.SpinwardenMain;
import Spinwarden.cards.BaseCard;
import Spinwarden.util.CardStats;

public class ChaosFive extends BaseCard {
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public static final String ID = makeID(ChaosFive.class.getSimpleName());
    public static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0);

    public ChaosFive() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
        tags.add(SpinwardenMain.MAGIC_DIE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1)));
    }

}
