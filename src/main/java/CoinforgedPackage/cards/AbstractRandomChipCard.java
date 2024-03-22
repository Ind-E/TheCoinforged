package CoinforgedPackage.cards;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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

public abstract class AbstractRandomChipCard extends AbstractCoinforgedCard implements CustomSavable<Integer> {
    protected ChipColor chip;
    protected boolean removeModifierOnPlay;
    protected HashMap<ChipColor, Integer> chipWeights = null;

    public static ChipColor getRandomChip(HashMap<ChipColor, Integer> chipWeights) {
        if (!CardCrawlGame.isInARun() || AbstractDungeon.miscRng == null) {
            return null;
        } else if (chipWeights == null) {
            return intToChip(AbstractDungeon.miscRng.random(ChipColor.values().length - 1));
        } else {
            ArrayList<ChipColor> weightedChipColors = new ArrayList<>();
            for (Map.Entry<ChipColor, Integer> entry : chipWeights.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    weightedChipColors.add(entry.getKey());
                }
            }
            return weightedChipColors.get(AbstractDungeon.miscRng.random(weightedChipColors.size() - 1));
        }

    }

    protected HashMap<ChipColor, Integer> getChipWeights() {
        HashMap<ChipColor, Integer> weights = new HashMap<>();
        for (ChipColor color : ChipColor.values()) {
            weights.put(color, 1); // Default weight
        }
        return weights;
    }

    public AbstractRandomChipCard(String ID, CardStats info, boolean removeModifierOnPlay) {
        this(ID, info, getRandomChip(null), null, removeModifierOnPlay);
    }

    public AbstractRandomChipCard(String ID, CardStats info) {
        this(ID, info, getRandomChip(null), null, true);
    }

    public AbstractRandomChipCard(String ID, CardStats info, HashMap<ChipColor, Integer> chipWeights,
            boolean removeModifierOnPlay) {
        this(ID, info, getRandomChip(chipWeights), chipWeights, removeModifierOnPlay);
    }

    public AbstractRandomChipCard(String ID, CardStats info, ChipColor chip) {
        this(ID, info, chip, null, true);
    }

    public AbstractRandomChipCard(String ID, CardStats info, ChipColor chip, HashMap<ChipColor, Integer> chipWeights,
            boolean removeModifierOnPlay) {
        super(ID, info);
        this.chip = chip;
        this.removeModifierOnPlay = removeModifierOnPlay;
        this.chipWeights = chipWeights;
        if (this.chipWeights == null) {
            this.chipWeights = getChipWeights();
        }

        try {
            this.cardsToPreview = getCardToPreview(this.chip);
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
        }

        try {
            CardModifierManager.addModifier(this, getModifier(this.chip, removeModifierOnPlay));
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
        }
    }

    protected static AbstractCardModifier getModifier(ChipColor chip, boolean removeModifierOnPlay) {
        switch (chip) {
            case Blue:
                return new MakeBlueChipModifier(removeModifierOnPlay);
            case Black:
                return new MakeBlackChipModifier(removeModifierOnPlay);
            case Cracked:
                return new MakeCrackedChipModifier(removeModifierOnPlay);
            case Gray:
                return new MakeGrayChipModifier(removeModifierOnPlay);
            case Green:
                return new MakeGreenChipModifier(removeModifierOnPlay);
            case Orange:
                return new MakeOrangeChipModifier(removeModifierOnPlay);
            case Red:
                return new MakeRedChipModifier(removeModifierOnPlay);
            case White:
                return new MakeWhiteChipModifier(removeModifierOnPlay);
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

    public abstract AbstractCard makeCopy();

    @Override
    public Integer onSave() {
        return chipToInt(this.chip);
    }

    @Override
    public Type savedType() {
        return new TypeToken<Integer>() {
        }.getType();
    }

    @Override
    public void onLoad(Integer n) {
        this.chip = intToChip(n);
    }

    public enum ChipColor {
        Black, // 0
        Blue, // 1
        Cracked, // 2
        Gray, // 3
        Green, // 4
        Orange, // 5
        Red, // 6
        White // 7
    }

}
