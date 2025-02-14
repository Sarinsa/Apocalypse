package toast.apocalypse.client;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import toast.apocalypse.core.ApocalypseMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBreecher extends RenderCreeper {

    public static final ResourceLocation GRUMP_TEXTURE = ApocalypseMod.resourceLoc("textures/entity/breecher.png");

    public RenderBreecher() {
        super();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return RenderBreecher.GRUMP_TEXTURE;
    }
}