package pl.mobdrops.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mobdrops.MobDrops;

public class Commands implements CommandExecutor {

    private final MobDrops plugin = MobDrops.getInstance();
    private final String prefix = plugin.getDataHandler().getPrefix();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean isPlayer = false;
        if(sender instanceof Player) {
            if(!sender.hasPermission(plugin.getPermissionManager().getPermission("mobdrops.manage"))) {
                sender.sendMessage(prefix + "§cYou don't have permission!");
                return true;
            }
            isPlayer = true;
        }
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("reload")) {
                plugin.getDataHandler().loadConfig();
                sender.sendMessage(prefix + "§aReloaded!");
            } else if(args[0].equalsIgnoreCase("giveitem")) {
                if(!isPlayer) {
                    sender.sendMessage(prefix + "§cYou must be a player!");
                    return true;
                }
                if(args.length < 2) {
                    sender.sendMessage(prefix + "§cCorrect usage: /mobdrops giveitem <itemID>");
                    return true;
                }
                boolean found = false;
                for(String key : plugin.getItemManager().getItems().keySet()) {
                    if(args[1].equalsIgnoreCase(key)) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    sender.sendMessage(prefix + "§cCannot find item: " + args[1]);
                    return true;
                }
                ((Player) sender).getInventory().addItem(plugin.getItemManager().getItems().get(args[1]).getItemStack());
                sender.sendMessage(prefix + "§aDone! Check out your inventory!");
            } else {
                ShowHelp(sender);
            }
        }
        return true;
    }

    public void ShowHelp(CommandSender sender) {

    }
}
