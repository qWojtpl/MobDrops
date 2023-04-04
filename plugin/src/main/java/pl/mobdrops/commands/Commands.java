package pl.mobdrops.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mobdrops.MobDrops;
import pl.mobdrops.items.CustomItem;
import pl.mobdrops.mobs.MobDrop;

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
            } else if(args[0].equalsIgnoreCase("items")) {
                sender.sendMessage("§7========== §cMobDrops §7==========");
                sender.sendMessage("");
                for(String key : plugin.getItemManager().getItems().keySet()) {
                    CustomItem ci = plugin.getItemManager().getItems().get(key);
                    sender.sendMessage("§c" + key);
                    sender.sendMessage(" ├ §7Name §c-> §7" + ci.getName().replace("§", "&"));
                    sender.sendMessage(" ├ §7Lore §c-> §7" + ci.getLore().replace("§", "&"));
                    sender.sendMessage(" ├ §7Unbreakable §c-> §7" + ci.isUnbreakable());
                    sender.sendMessage(" └ §7Enchantments §c-> §7" + ci.getStringEnchantments());
                }
                sender.sendMessage("");
                sender.sendMessage("§7========== §cMobDrops §7==========");
            } else if(args[0].equalsIgnoreCase("mobs")) {
                sender.sendMessage("§7========== §cMobDrops §7==========");
                sender.sendMessage("");
                for(String key : plugin.getMobsManager().getMobs().keySet()) {
                    sender.sendMessage("§c" + key);
                    int i = 0;
                    for(MobDrop md : plugin.getMobsManager().getMobs().get(key)) {
                        sender.sendMessage(" ├ §c" + i);
                        sender.sendMessage("  ├ §7itemID §c-> §7" + md.getItemID());
                        sender.sendMessage("  ├ §7Percentage §c-> §7" + md.getPercentage());
                        sender.sendMessage("  ├ §7Count min §c-> §7" + md.getCountMin());
                        sender.sendMessage("  ├ §7Count max §c-> §7" + md.getCountMax());
                        sender.sendMessage("  ├ §7Use firework §c-> §7" + md.isFirework());
                        sender.sendMessage("  └ §7Firework color §c-> §7" + md.getFireworkColor());
                        i++;
                    }
                }
                sender.sendMessage("");
                sender.sendMessage("§7========== §cMobDrops §7==========");
            } else {
                ShowHelp(sender);
            }
        } else {
            ShowHelp(sender);
        }
        return true;
    }

    public void ShowHelp(CommandSender sender) {
        sender.sendMessage("§7========== §cMobDrops §7==========");
        sender.sendMessage("");
        sender.sendMessage("§c/mobdrops reload - §7Reload configuration");
        sender.sendMessage("§c/mobdrops giveitem <item> - §7Get custom item");
        sender.sendMessage("§c/mobdrops items - §7Show all custom items");
        sender.sendMessage("§c/mobdrops mobs - §7Show all mobs configuration");
        sender.sendMessage("");
        sender.sendMessage("§7========== §cMobDrops §7==========");
    }
}
