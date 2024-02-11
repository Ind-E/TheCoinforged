package GamblerMod.cards;

import static com.megacrit.cardcrawl.powers.AbstractPower.DESCRIPTIONS;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import GamblerMod.character.Gambler;
import GamblerMod.util.CardStats;

public class FullHouse extends BaseCard{
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public static final String ID = makeID(FullHouse.class.getSimpleName());
    private static final CardStats info = new CardStats(
        Gambler.Enums.CARD_COLOR, 
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        1
    );

    public FullHouse() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = getTypes();
        for (int i = 0; i < count; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = getTypes();
        if (count == 1) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + count + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + count + cardStrings.EXTENDED_DESCRIPTION[2];
        } 
        initializeDescription();
    }

    public int getTypes() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<String> types = new ArrayList<String>();
        for (AbstractCard c : p.hand.group) {
            if (!types.contains(c.type.toString())) {
                types.add(c.type.toString());
            }
        }
        return types.size();
    }
}