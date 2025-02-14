package toast.apocalypse.item;

import java.util.List;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import toast.apocalypse.core.ApocalypseMod;

/**
 * The bucket helmet item. Can be equipped on the head for decent armor, at the cost of obscurring vision greatly.
 */
public class ItemBucketHelm extends ItemArmor {

    private static final String[] BUCKET_ARMOR = {
            ApocalypseMod.resourceLoc("textures/models/armor/bucket_layer_1.png").toString(),
            ApocalypseMod.resourceLoc("textures/models/armor/bucket_layer_2.png").toString(),
    };

    /** Constructs a generic ItemBucketHelm and registers its armor render file. */
    public ItemBucketHelm() {
        super(ItemArmor.ArmorMaterial.IRON, ApocalypseMod.proxy.getRenderIndex("bucket", 2), 0);
        this.setMaxDamage(this.getMaxDamage() >> 1);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return slot == 2 ? BUCKET_ARMOR[1] : BUCKET_ARMOR[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY) {
        ApocalypseMod.proxy.renderBucketBlur(resolution.getScaledWidth(), resolution.getScaledHeight());
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean detailedInfo) {
        list.add("\u00a77Rain Protection");
    }
}
