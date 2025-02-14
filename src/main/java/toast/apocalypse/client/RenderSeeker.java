package toast.apocalypse.client;

import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import toast.apocalypse.core.ApocalypseMod;
import toast.apocalypse.entity.EntitySeeker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSeeker extends RenderGhast {

    public static final ResourceLocation SEEKER_TEXTURE = ApocalypseMod.resourceLoc("textures/entity/seeker.png");
    public static final ResourceLocation SEEKER_FIRE_TEXTURE = ApocalypseMod.resourceLoc("textures/entity/seeker_fire.png");

    public RenderSeeker() {
        super();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (((EntitySeeker)entity).getFireTexture() == 1)
            return RenderSeeker.SEEKER_FIRE_TEXTURE;
        return RenderSeeker.SEEKER_TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTick) {
        GL11.glScalef(4.0F, 4.0F, 4.0F);
    }
}