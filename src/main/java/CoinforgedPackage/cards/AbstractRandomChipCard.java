package CoinforgedPackage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import CoinforgedPackage.cards.chips.BlackChip;
import CoinforgedPackage.cards.chips.BlueChip;
import CoinforgedPackage.cards.chips.CrackedChip;
import CoinforgedPackage.cards.chips.GrayChip;
import CoinforgedPackage.cards.chips.GreenChip;
import CoinforgedPackage.cards.chips.OrangeChip;
import CoinforgedPackage.cards.chips.RedChip;
import CoinforgedPackage.cards.chips.WhiteChip;
import CoinforgedPackage.modifiers.MakeBlackChipModifier;
import CoinforgedPackage.modifiers.MakeBlueChipModifier;
import CoinforgedPackage.modifiers.MakeCrackedChipModifier;
import CoinforgedPackage.modifiers.MakeGrayChipModifier;
import CoinforgedPackage.modifiers.MakeGreenChipModifier;
import CoinforgedPackage.modifiers.MakeOrangeChipModifier;
import CoinforgedPackage.modifiers.MakeRedChipModifier;
import CoinforgedPackage.modifiers.MakeWhiteChipModifier;
import CoinforgedPackage.util.CardStats;
import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomSavable;
import basemod.helpers.CardModifierManager;

public abstract class AbstractRandomChipCard extends AbstractCoinforgedCard implements CustomSavable<Integer>{
    protected ChipColor chip;

    public AbstractRandomChipCard(String ID, CardStats info) {
        this(ID, info, !CardCrawlGame.isInARun() || AbstractDungeon.miscRng == null ? null : intToChip(AbstractDungeon.miscRng.random(ChipColor.values().length - 1)));
    }
    
    public AbstractRandomChipCard(String ID, CardStats info, ChipColor chip) {
        super(ID, info);
        this.chip = chip;
        this.cardsToPreview = getCardToPreview(this.chip);
        try {
            CardModifierManager.addModifier(this, getModifier(this.chip));
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
        }
        // CardModifierManager.addModifier(this, getModifier(this.chip));
    }

    @Override
    public void use(AbstractPlayer arg0, AbstractMonster arg1) {
        AbstractCardModifier chipToMake = getModifier(this.chip);
        if (chipToMake != null) {
            CardModifierManager.addModifier(this, chipToMake);
        }
    }

    protected static AbstractCardModifier getModifier(ChipColor chip) {
        switch (chip) {
            case Blue:
                return new MakeBlueChipModifier();
            case Black:
                return new MakeBlackChipModifier();
            case Cracked:
                return new MakeCrackedChipModifier();
            case Gray:
                return new MakeGrayChipModifier();
            case Green:
                return new MakeGreenChipModifier();
            case Orange:
                return new MakeOrangeChipModifier();
            case Red:
                return new MakeRedChipModifier();
            case White:
                return new MakeWhiteChipModifier();
            default:
                return null;
        }
    }

    protected static AbstractCard getCardToPreview(ChipColor chip) {
        switch (chip) {
            case Blue:
                return new BlueChip();
            case Black:
                return new BlackChip();
            case Cracked:
                return new CrackedChip();
            case Gray:
                return new GrayChip();
            case Green:
                return new GreenChip();
            case Orange:
                return new OrangeChip();
            case Red:
                return new RedChip();
            case White:
                return new WhiteChip();
            default:
                return null;
        }
    }

    protected static ChipColor intToChip(int n) {
        switch (n) {
            case 0:
                return ChipColor.Black;
            case 1:
                return ChipColor.Blue;
            case 2:
                return ChipColor.Cracked;
            case 3:
                return ChipColor.Gray;
            case 4:
                return ChipColor.Green;
            case 5:
                return ChipColor.Orange;
            case 6:
                return ChipColor.Red;
            case 7:
                return ChipColor.White;
            default:
                return null;
        }
    }

    protected static Integer chipToInt(ChipColor chip) {
        switch (chip) {
            case Black:
                return 0;
            case Blue:
                return 1;
            case Cracked:
                return 2;
            case Gray:
                return 3;
            case Green:
                return 4;
            case Orange:
                return 5;
            case Red:
                return 6;
            case White:
                return 7;
            default:
                return null;
        }
    }

    @Override
    public Integer onSave() {
        return chipToInt(this.chip);
    }

    @Override
    public void onLoad(Integer n) {
        this.chip = intToChip(n);
        CardModifierManager.addModifier(this, getModifier(chip));
    }

    public enum ChipColor {
        Black,   // 0
        Blue,    // 1
        Cracked, // 2
        Gray,    // 3
        Green,   // 4
        Orange,  // 5
        Red,     // 6
        White    // 7
    }

    

}
