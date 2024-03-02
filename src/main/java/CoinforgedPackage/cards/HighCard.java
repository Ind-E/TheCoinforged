package CoinforgedPackage.cards;

import static CoinforgedPackage.util.GeneralUtils.removePrefix;
import static CoinforgedPackage.util.TextureLoader.getCardTextureString;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.character.Coinforged;
import CoinforgedPackage.util.CardStats;

public class HighCard extends BaseCard {
    private static final int DAMAGE = 12;
    private static final int MAGIC = 12;
    private static final int UPG_MAGIC = 6;
    private int counter = 0;

    public static final String ID = makeID(HighCard.class.getSimpleName());
    private static final CardStats info = new CardStats(
            Coinforged.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1);

    public HighCard() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (counter % 2 == 0) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                    AttackEffect.NONE));
        } else {
            addToBot(new ModifyDamageAction(this.uuid, this.magicNumber));
        }
        counter++;
        if (counter % 2 == 0) {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.type = CardType.ATTACK;
            this.target = CardTarget.ENEMY;
            String img = getCardTextureString(removePrefix(ID), type);
            if (img != null) {
                this.loadCardImage(img);
            }
        } else {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            this.type = CardType.SKILL;
            this.target = CardTarget.NONE;
            String img = getCardTextureString(removePrefix(ID), type);
            if (img != null) {
                this.loadCardImage(img);
            }
        }
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new HighCard();
    }
}