package toast.apocalypse.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import toast.apocalypse.network.message.MessageWorldDifficulty;

public class PacketHandler {

    /** The network channel for Apocalypse. */
    public static SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("Apocalypse|Info");

    private static int ID = 0;


    public static void registerMessages() {
        register(MessageWorldDifficulty.Handler.class, MessageWorldDifficulty.class, Side.CLIENT);
    }


    private static <REQ extends IMessage, REPLY extends IMessage> void register(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
        CHANNEL.registerMessage(messageHandler, requestMessageType, ID++, side);
    }
}
