package Spinwarden;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.evacipated.cardcrawl.mod.stslib.patches.CustomTargeting;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import Spinwarden.cards.BaseCard;
import Spinwarden.character.SpinwardenCharacter;
import Spinwarden.potions.BasePotion;
import Spinwarden.relics.BaseRelic;
import Spinwarden.util.GeneralUtils;
import Spinwarden.util.KeywordInfo;
import Spinwarden.util.TextureLoader;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

@SpireInitializer
public class SpinwardenMain implements
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        PostInitializeSubscriber,
        EditCharactersSubscriber {
    public static ModInfo info;
    public static String modID; // Edit your pom.xml to change this
    static {
        loadModInfo();
    }
    public static final Logger logger = LogManager.getLogger(modID); // Used to output to the console.
    private static final String resourcesFolder = "SpinwardenMod";

    @SpireEnum
    public static AbstractCard.CardTags DIE;
    @SpireEnum
    public static AbstractCard.CardTags BLUE_DIE;
    @SpireEnum
    public static AbstractCard.CardTags RED_DIE;
    @SpireEnum
    public static AbstractCard.CardTags GREEN_DIE;
    @SpireEnum
    public static AbstractCard.CardTags PURPLE_DIE;
    @SpireEnum
    public static AbstractCard.CardTags MAGIC_DIE;
    @SpireEnum
    public static AbstractCard.CardTags RIGGED;
    @SpireEnum
    public static AbstractCard.CardTags MARKED;
    @SpireEnum
    public static AbstractCard.CardTags POKER_CHIP;

    private static final String BG_ATTACK = characterPath("cardback/bg_attack.png");
    private static final String BG_ATTACK_P = characterPath("cardback/bg_attack_p.png");
    private static final String BG_SKILL = characterPath("cardback/bg_skill.png");
    private static final String BG_SKILL_P = characterPath("cardback/bg_skill_p.png");
    private static final String BG_POWER = characterPath("cardback/bg_power.png");
    private static final String BG_POWER_P = characterPath("cardback/bg_power_p.png");
    private static final String ENERGY_ORB = characterPath("cardback/energy_orb.png");
    private static final String ENERGY_ORB_P = characterPath("cardback/energy_orb_p.png");
    private static final String SMALL_ORB = characterPath("cardback/small_orb.png");

    private static final String CHAR_SELECT_BUTTON = characterPath("select/button.png");
    private static final String CHAR_SELECT_PORTRAIT = characterPath("select/portrait.png");

    private static final Color cardColor = new Color(214f / 255f, 214f / 255f, 84f / 255f, 1f);
    // red, green, blue, alpha. alpha is transparency, which should just be 1.

    // This is used to prefix the IDs of various objects like cards and relics,
    // to avoid conflicts between different mods using the same name for things.
    public static String makeID(String id) {
        return modID + ":" + id;
    }

    // This will be called by ModTheSpire because of the @SpireInitializer
    // annotation at the top of the class.
    public static void initialize() {
        new SpinwardenMain();

        BaseMod.addColor(SpinwardenCharacter.Enums.CARD_COLOR, cardColor,
                BG_ATTACK, BG_SKILL, BG_POWER, ENERGY_ORB,
                BG_ATTACK_P, BG_SKILL_P, BG_POWER_P, ENERGY_ORB_P,
                SMALL_ORB);
    }

    public SpinwardenMain() {
        BaseMod.subscribe(this); // This will make BaseMod trigger all the subscribers at their appropriate
                                 // times.
        logger.info(modID + " subscribed to BaseMod.");
    }

    @Override
    public void receivePostInitialize() {
        // This loads the image used as an icon in the in-game mods menu.
        Texture badgeTexture = TextureLoader.getTexture(imagePath("badge.png"));
        // Set up the mod information displayed in the in-game mods menu.
        // The information used is taken from your pom.xml file.
        BaseMod.registerModBadge(badgeTexture, info.Name, GeneralUtils.arrToString(info.Authors), info.Description,
                null);
        registerPotions();
        CustomTargeting.registerCustomTargeting(SelfOrEnemyTargeting.SELF_OR_ENEMY, new SelfOrEnemyTargeting());

        if (System.getProperty("user.name").equals("sacha")) {
            countCards();
        }
    }

    public static void countCards() {
        String filePath = "C:\\Users\\sacha\\Documents\\GitHub\\SpinwardenMod\\card_data.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header to the CSV file
            writer.write("Name,Cost,Type,Rarity\n");

            // Loop through the card list and write data to the CSV file
            for (AbstractCard c : CardLibrary.getCardList(CardLibrary.LibraryType.valueOf("SPINWARDEN_COLOR"))) {
                String cardName = c.name;
                String cardType = c.type.toString();
                String cardRarity = c.rarity.toString();
                int cardCost = c.cost;

                // Write card information to the CSV file
                writer.write(String.format("%s,%s,%s,%s\n", cardName, cardCost, cardType, cardRarity));
            }

            System.out.println("Card data exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerPotions() {
        new AutoAdd(modID) // Loads files from this mod
                .packageFilter(BasePotion.class) // In the same package as this class
                .any(BasePotion.class, (info, potion) -> { // Run this code for any classes that extend this class
                    // These three null parameters are colors.
                    // If they're not null, they'll overwrite whatever color is set in the potions
                    // themselves.
                    // This is an old feature added before having potions determine their own color
                    // was possible.
                    BaseMod.addPotion(potion.getClass(), null, null, null, potion.ID, potion.playerClass);
                    // playerClass will make a potion character-specific. By default, it's null and
                    // will do nothing.
                });
    }

    @Override
    public void receiveEditRelics() { // somewhere in the class
        new AutoAdd(modID) // Loads files from this mod
                .packageFilter(BaseRelic.class) // In the same package as this class
                .any(BaseRelic.class, (info, relic) -> { // Run this code for any classes that extend this class
                    if (relic.pool != null)
                        BaseMod.addRelicToCustomPool(relic, relic.pool); // Register a custom character specific relic
                    else
                        BaseMod.addRelic(relic, relic.relicType); // Register a shared or base game character specific
                                                                  // relic

                    // If the class is annotated with @AutoAdd.Seen, it will be marked as seen,
                    // making it visible in the relic library.
                    // If you want all your relics to be visible by default, just remove this if
                    // statement.
                    if (info.seen)
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                });
    }

    /*----------Localization----------*/

    // This is used to load the appropriate localization files based on language.
    private static String getLangString() {
        return Settings.language.name().toLowerCase();
    }

    private static final String defaultLanguage = "eng";

    public static final Map<String, KeywordInfo> keywords = new HashMap<>();

    @Override
    public void receiveEditStrings() {
        /*
         * First, load the default localization.
         * Then, if the current language is different, attempt to load localization for
         * that language.
         * This results in the default localization being used for anything that might
         * be missing.
         * The same process is used to load keywords slightly below.
         */
        loadLocalization(defaultLanguage); // no exception catching for default localization; you better have at least
                                           // one that works.
        if (!defaultLanguage.equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            } catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLocalization(String lang) {
        // While this does load every type of localization, most of these files are just
        // outlines so that you can see how they're formatted.
        // Feel free to comment out/delete any that you don't end up using.
        BaseMod.loadCustomStringsFile(CardStrings.class,
                localizationPath(lang, "CardStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                localizationPath(lang, "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class,
                localizationPath(lang, "EventStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                localizationPath(lang, "OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                localizationPath(lang, "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                localizationPath(lang, "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                localizationPath(lang, "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class,
                localizationPath(lang, "UIStrings.json"));
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(localizationPath(defaultLanguage, "Keywords.json"))
                .readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            keyword.prep();
            registerKeyword(keyword);
        }

        if (!defaultLanguage.equals(getLangString())) {
            try {
                json = Gdx.files.internal(localizationPath(getLangString(), "Keywords.json"))
                        .readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    keyword.prep();
                    registerKeyword(keyword);
                }
            } catch (Exception e) {
                logger.warn(modID + " does not support " + getLangString() + " keywords.");
            }
        }
    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(modID.toLowerCase(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
        if (!info.ID.isEmpty()) {
            keywords.put(info.ID, info);
        }
    }

    // These methods are used to generate the correct filepaths to various parts of
    // the resources folder.
    public static String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public static String imagePath(String file) {
        return resourcesFolder + "/images/" + file;
    }

    public static String characterPath(String file) {
        return resourcesFolder + "/images/character/" + file;
    }

    public static String powerPath(String file) {
        return resourcesFolder + "/images/powers/" + file;
    }

    public static String relicPath(String file) {
        return resourcesFolder + "/images/relics/" + file;
    }

    // This determines the mod's ID based on information stored by ModTheSpire.
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo) -> {
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(),
                    Collections.emptySet());
            return initializers.contains(SpinwardenMain.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        } else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID) // Loads files from this mod
                .packageFilter(BaseCard.class) // In the same package as this class
                .setDefaultSeen(true) // And marks them as seen in the compendium
                .cards(); // Adds the cards
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new SpinwardenCharacter(),
                CHAR_SELECT_BUTTON, CHAR_SELECT_PORTRAIT, SpinwardenCharacter.Enums.Spinwarden);
    }

    public static AbstractCard generateRandomStatusCard() {
        List<AbstractCard> statusCards = new ArrayList<>();

        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (card.type == CardType.STATUS) {
                statusCards.add(card);
            }
        }

        if (!statusCards.isEmpty()) {
            AbstractCard randomStatusCard = statusCards
                    .get(AbstractDungeon.cardRandomRng.random(statusCards.size() - 1)).makeCopy();
            return randomStatusCard;
        }
        throw new RuntimeException("No Status cards found");
    }
}