package toast.apocalypse.core.register;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import toast.apocalypse.core.ApocalypseMod;
import toast.apocalypse.core.config.Properties;
import toast.apocalypse.entity.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ApocalypseEntities {

    private static final Map<Class<? extends EntityLiving>, Integer[]> EGG_INFO = new HashMap<Class<? extends EntityLiving>, Integer[]>();

    /** Increments each time an entity is registered to get a new unique ID */
    private static int ID = 0;


    /**
     * Registers Apocalypse's entities.<br>
     * <br>
     * Called from {@link ApocalypseMod#init(FMLInitializationEvent)}
     */
    public static void registerEntities(final Object modContainer) {
        registerEntityWithEgg(EntityBreecher.class, "Breecher", 0x0da70b, 0xf9f9f9, modContainer, 80, 3, true);
        registerEntityWithEgg(EntityGrump.class, "Grump", 0xf9f9f9, 0x2d41f4, modContainer, 80, 3, true);
        registerEntityWithEgg(EntitySeeker.class, "Seeker", 0xf9f9f9, 0xa80e0e, modContainer, 80, 3, true);
        registerEntityWithEgg(EntityGhost.class, "Ghost", 0xbcbcbc, 0x708899, modContainer, 80, 3, true);
        registerEntityWithEgg(EntityDestroyer.class, "Destroyer", 0x7d7d7d, 0xa80e0e, modContainer, 80, 3, true);


        registerEntity(EntitySeekerFireball.class, "SeekerFireball", modContainer, 64, 10, true);
        registerEntity(EntityDestroyerFireball.class, "DestroyerFireball", modContainer, 64, 10, true);
        registerEntity(EntityMonsterFishHook.class, "FishHook", modContainer, 64, 5, true);

        registerSpawnEggs();
    }

    /** Registers an entity without a spawn egg. */
    private static void registerEntity(Class<? extends Entity> entityClass, String regName, Object modContainer, int trackingRange, int updateInterval, boolean sendVelocityUpdate) {
        EntityRegistry.registerModEntity(entityClass, regName, ID++, modContainer, trackingRange, updateInterval, sendVelocityUpdate);
    }

    /** Registers a living entity with a spawn egg. */
    private static void registerEntityWithEgg(Class<? extends EntityLiving> entityClass, String regName, int primaryColor, int secondaryColor, Object modContainer, int trackingRange, int updateInterval, boolean sendVelocityUpdate) {
        registerEntity(entityClass, regName, modContainer, trackingRange, updateInterval, sendVelocityUpdate);
        EGG_INFO.put(entityClass, new Integer[] { primaryColor, secondaryColor });
    }

    @SuppressWarnings("unchecked")
    private static void registerSpawnEggs() {
        boolean makeSpawnEggs = Properties.getBoolean(Properties.GENERAL, "spawn_eggs");
        Method eggIdClaimer;
        int eggId;

        if (makeSpawnEggs) {
            try {
                eggIdClaimer = EntityRegistry.class.getDeclaredMethod("validateAndClaimId", int.class);
                eggIdClaimer.setAccessible(true);
            }
            catch (Exception ex) {
                ApocalypseMod.log("Error claiming spawn egg ID! Spawn eggs will probably be overwritten.");
                ex.printStackTrace();
                return;
            }

            for (Class<? extends EntityLiving> entityClass : EGG_INFO.keySet()) {
                eggId = EntityRegistry.findGlobalUniqueEntityId();
                try {
                    if (eggIdClaimer != null) {
                        eggId = (Integer) eggIdClaimer.invoke(EntityRegistry.instance(), eggId);
                    }
                }
                catch (Exception ex) {
                    // Do nothing
                }
                Integer[] eggColors = EGG_INFO.get(entityClass);

                EntityList.IDtoClassMapping.put(eggId, entityClass);
                EntityList.entityEggs.put(eggId, new EntityList.EntityEggInfo(eggId, eggColors[0], eggColors[1]));
            }
        }
    }
}
