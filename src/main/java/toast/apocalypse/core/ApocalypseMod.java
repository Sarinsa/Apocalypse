package toast.apocalypse.core;

import java.io.File;
import java.util.Random;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import toast.apocalypse.command.CommandSetDifficulty;
import toast.apocalypse.network.PacketHandler;
import toast.apocalypse.core.register.ApocalypseEntityHandler;
import toast.apocalypse.core.register.ApocalypseItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import toast.apocalypse.core.config.PropHelper;
import toast.apocalypse.core.config.Properties;

/**
 * This is the mod class. Everything the mod does is initialized by this class.
 */
@Mod(modid = ApocalypseMod.MODID, name = "Apocalypse", version = ApocalypseMod.VERSION)
public class ApocalypseMod {

    /* TO DO *\
	 * Improve ghast AI by not checking the entire distance when attempting to move towards the player.
	
     * Equipment drop/pickup chance?
     * Drop rate?
     * XP?

     * HUD displays months in SSP?
     * Lunar clock?

     * Full moon "event"
       > Monsters auto-target players
	   > Something to better defeat underwater/underground bases
	   > Something to better defeat lava
    \* ** ** */

    /** This mod's id. */
    public static final String MODID = "Apocalypse";

    /** This mod's version. */
    public static final String VERSION = "0.0.7";

    /** If true, this mod starts up in debug mode. */
    public static boolean DEBUG_MODE = false;

    /** This mod's sided proxy. */
    @SidedProxy(clientSide = "toast.apocalypse.client.ClientProxy", serverSide = "toast.apocalypse.core.CommonProxy")
    public static CommonProxy proxy;

    /** The random number generator for this mod. */
    public static final Random random = new Random();



    /** Called before initialization. Loads the properties/configurations. */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Properties.init(new File(event.getModConfigurationDirectory(), ApocalypseMod.MODID));
        ApocalypseMod.DEBUG_MODE = Properties.getBoolean(Properties.GENERAL, "debug");
        ApocalypseMod.logDebug("Loading in debug mode!");
        PropHelper.init();

        ApocalypseItems.registerItems();
        PacketHandler.registerMessages();
    }

    /** Called during initialization. Registers entities, mob spawns, and renderers. */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        new WorldDifficultyManager();
        new EventHandler();
        ApocalypseEntityHandler.registerEntities(this);
        ApocalypseMod.proxy.registerRenderers();

        GameRegistry.addShapelessRecipe(new ItemStack(ApocalypseItems.BUCKET_HELMET), Items.bucket);
    }

    /** Called as the server is starting. */
    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        WorldDifficultyManager.init(event.getServer());

        ServerCommandManager commandManager = (ServerCommandManager)event.getServer().getCommandManager();
        commandManager.registerCommand(new CommandSetDifficulty());
    }

    /** Called as the server is stopping. */
    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        WorldDifficultyManager.cleanup();
    }


    /** Prints the message to the console with this mod's name tag. */
    public static void log(String message) {
        System.out.println("[" + ApocalypseMod.MODID + "] " + message);
    }
    /** Prints the message to the console with this mod's name tag if debugging is enabled. */

    public static void logDebug(String message) {
        if (ApocalypseMod.DEBUG_MODE) {
            System.out.println("[" + ApocalypseMod.MODID + "] [debug] " + message);
        }
    }

    /** Prints the message to the console with this mod's name tag and a warning tag. */
    public static void logWarning(String message) {
        System.out.println("[" + ApocalypseMod.MODID + "] [WARNING] " + message);
    }

    /** Prints the message to the console with this mod's name tag and an error tag.<br>
     * Throws a runtime exception with a message and this mod's name tag if debugging is enabled. */
    public static void logError(String message) {
        if (ApocalypseMod.DEBUG_MODE)
            throw new RuntimeException("[" + ApocalypseMod.MODID + "] " + message);
        ApocalypseMod.log("[ERROR] " + message);
    }

    /** Throws a runtime exception with a message and this mod's name tag. */
    public static void exception(String message) {
        throw new RuntimeException("[" + ApocalypseMod.MODID + "] " + message);
    }

    /** Returns a new ResourceLocation with Apocalypse's namespace. */
    public static ResourceLocation resourceLoc(String path) {
        return new ResourceLocation(MODID.toLowerCase(), path);
    }
}
