package pl.mobdrops.data;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.mobdrops.MobDrops;
import pl.mobdrops.items.CustomItem;
import pl.mobdrops.items.ItemManager;

import java.io.File;
import java.util.List;

public class DataHandler {

    private final MobDrops plugin = MobDrops.getInstance();

    public void loadConfig() {
        loadItems();
    }

    public void loadItems() {
        File itemsFile = new File(plugin.getDataFolder(), "items.yml");
        if(!itemsFile.exists()) {
            plugin.saveResource("items.yml", false);
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(itemsFile);
        ConfigurationSection section = yml.getConfigurationSection("items");
        if(section == null) return;
        for(String key : section.getKeys(false)) {
            String strMaterial = yml.getString("items." + key + ".item");
            if(strMaterial == null) {
                strMaterial = "dirt";
                plugin.getLogger().severe("Item is null! (Reading " + key + ")");
            }
            Material material = Material.getMaterial(strMaterial.toUpperCase());
            if(material == null) {
                material = Material.DIRT;
                plugin.getLogger().severe("Cannot compare " + strMaterial + " with a correct material! "
                        + "(Reading " + key + ")");
            }
            String name = yml.getString("items." + key + ".name");
            String lore = yml.getString("items." + key + ".lore");
            List<String> enchantments = yml.getStringList("items." + key + ".enchantments");
            boolean unbreakable = yml.getBoolean("items." + key + ".unbreakable");
            plugin.getItemManager().getItems().put(key, new CustomItem(key, material, name, lore, enchantments, unbreakable));
        }
    }

}
