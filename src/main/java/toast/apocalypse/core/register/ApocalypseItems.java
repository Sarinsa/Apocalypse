package toast.apocalypse.core.register;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import toast.apocalypse.item.ItemBucketHelm;
import toast.apocalypse.core.ApocalypseMod;

public class ApocalypseItems {

    /** The bucket helmet. */
    public static Item BUCKET_HELMET;


    public static void registerItems() {
        BUCKET_HELMET = registerItem("bucketHelm", new ItemBucketHelm().setCreativeTab(CreativeTabs.tabCombat).setTextureName(texture("bucket_helm")));
    }

    private static <T extends Item> T registerItem(String regName, T item) {
        GameRegistry.registerItem(item.setUnlocalizedName(regName), regName);
        return item;
    }

    private static String texture(String name) {
        return ApocalypseMod.resourceLoc(name).toString();
    }
}
