package pl.mobdrops;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mobdrops.commands.CommandHelper;
import pl.mobdrops.commands.Commands;
import pl.mobdrops.data.DataHandler;
import pl.mobdrops.events.Events;
import pl.mobdrops.items.ItemManager;
import pl.mobdrops.permissions.PermissionManager;

@Getter
public final class MobDrops extends JavaPlugin {

    private static MobDrops main;
    private PermissionManager permissionManager;
    private ItemManager itemManager;
    private DataHandler dataHandler;

    @Override
    public void onEnable() {
        main = this;
        this.permissionManager = new PermissionManager();
        this.itemManager = new ItemManager();
        this.dataHandler = new DataHandler();
        dataHandler.loadConfig();
        getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("mobdrops").setExecutor(new Commands());
        getCommand("mobdrops").setTabCompleter(new CommandHelper());
        getLogger().info("Loaded.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Bye!");
    }

    public static MobDrops getInstance() {
        return main;
    }
}
