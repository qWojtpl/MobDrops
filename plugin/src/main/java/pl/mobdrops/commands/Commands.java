package pl.mobdrops.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mobdrops.MobDrops;

public class Commands implements CommandExecutor {

    private final MobDrops plugin = MobDrops.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(!sender.hasPermission(plugin.getPermissionManager().getPermission("mobdrops.manage"))) {
                sender.sendMessage("§cYou don't have permission!");
                return true;
            }
        }

        return true;
    }
}
