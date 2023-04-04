package pl.mobdrops.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import pl.mobdrops.MobDrops;
import pl.mobdrops.items.CustomItem;

import java.util.ArrayList;
import java.util.List;

public class CommandHelper implements TabCompleter {

    private final MobDrops plugin = MobDrops.getInstance();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(!sender.hasPermission(plugin.getPermissionManager().getPermission("mobdrops.manage"))) {
                return null;
            }
        }
        List<String> completions = new ArrayList<>();
        if(args.length == 1) {
            completions.add("giveitem");
            completions.add("items");
            completions.add("mobs");
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("giveitem")) {
                completions.addAll(plugin.getItemManager().getItems().keySet());
            }
        }
        return StringUtil.copyPartialMatches(args[args.length-1], completions, new ArrayList<>());
    }
}
