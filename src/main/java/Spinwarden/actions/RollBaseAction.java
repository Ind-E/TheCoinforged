package Spinwarden.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Spinwarden.powers.LoadedDicePower;

public abstract class RollBaseAction extends AbstractGameAction {
    public int minroll = 1;
    public int maxroll = 6;
    public int magic = 0;
    public AbstractPlayer player;

    public RollBaseAction(AbstractCreature owner, int magic) {
        this(owner, magic, 1, 6);
    }

    public RollBaseAction(AbstractCreature owner, int magic, int minroll, int maxroll) {
        this.player = AbstractDungeon.player;
        this.magic = magic;
        this.minroll = minroll;
        this.maxroll = maxroll;
    }

    public abstract AbstractCard roll();

    public void update() {
        for (int i = 0; i < magic; i++) {
            AbstractCard rolledCard = roll();
            if (player.hasPower(LoadedDicePower.POWER_ID)) {
                int amount = player.getPower(LoadedDicePower.POWER_ID).amount;
                rolledCard.baseMagicNumber += amount;
                rolledCard.magicNumber = rolledCard.baseMagicNumber;
                rolledCard.baseDamage += amount;
                rolledCard.damage = rolledCard.baseDamage;
                rolledCard.baseBlock += amount;
                rolledCard.block = rolledCard.baseBlock;

                rolledCard.initializeDescription();
            }
            addToBot(new MakeTempCardInHandAction(rolledCard, 1));
        }
        this.isDone = true;
    }
}
