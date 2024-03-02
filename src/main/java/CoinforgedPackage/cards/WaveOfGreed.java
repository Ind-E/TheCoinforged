package CoinforgedPackage.cards;

import java.util.stream.Stream;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class WaveOfGreed extends BaseCard {
    private static final int DAMAGE = 26;
    private static final int UPG_DAMAGE = 6;
    private static final int GOLD = 10;

    public static final String ID = makeID(WaveOfGreed.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            3);

    public WaveOfGreed() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(GOLD);
        this.isMultiDamage = true;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        Stream<AbstractMonster> damagedEnemiesStream = AbstractDungeon.getMonsters().monsters.stream()
                .filter(monster -> !monster.isDeadOrEscaped() && !monster.halfDead);

        damagedEnemiesStream.forEach(monster -> {
            p.gainGold(this.magicNumber);
            for (int i = 0; i < this.magicNumber; i++)
                AbstractDungeon.effectList
                        .add(new GainPennyEffect(p, monster.hb.cX, monster.hb.cY, p.hb.cX, p.hb.cY, true));
        });
    }

    @Override
    public AbstractCard makeCopy() {
        return new WaveOfGreed();
    }

}
