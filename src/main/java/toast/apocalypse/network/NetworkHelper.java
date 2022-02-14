package toast.apocalypse.network;

import net.minecraft.entity.player.EntityPlayerMP;
import toast.apocalypse.network.message.MessageWorldDifficulty;

public class NetworkHelper {

    public static void sendDifficultyUpdate(long worldDifficulty, double multiplier, EntityPlayerMP player) {
        PacketHandler.CHANNEL.sendTo(new MessageWorldDifficulty(worldDifficulty, multiplier), player);
    }

    public static void sendDifficultyUpdateAll(long worldDifficulty) {
        PacketHandler.CHANNEL.sendToAll(new MessageWorldDifficulty(worldDifficulty));
    }

    public static void sendMultiplierUpdateAll(double multiplier) {
        PacketHandler.CHANNEL.sendToAll(new MessageWorldDifficulty(multiplier));
    }
}
