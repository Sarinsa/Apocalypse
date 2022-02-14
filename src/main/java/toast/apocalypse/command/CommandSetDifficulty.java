package toast.apocalypse.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import toast.apocalypse.core.WorldDifficultyManager;

/**
 * A command that sets the world difficulty level used by this mod.
 */
public class CommandSetDifficulty extends CommandBase {

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(sender);
    }

    @Override
    public String getCommandName() {
        return "setWorldDifficulty";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.Apocalypse.setWorldDifficulty.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
    	// Command takes a single argument, the new difficulty level to set (in days)

        if (args.length != 1)
            throw new WrongUsageException(this.getCommandUsage(sender));

        double arg;
        try {
            arg = Double.parseDouble(args[0]);
        }
        catch (Exception ex) {
            throw new WrongUsageException(this.getCommandUsage(sender));
        }
        WorldDifficultyManager.processSetCommand(arg);
        CommandBase.func_152373_a(sender, this, "commands.Apocalypse.setWorldDifficulty.success", arg);
    }
}