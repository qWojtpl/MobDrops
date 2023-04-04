package pl.mobdrops.data;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.mobdrops.MobDrops;
import pl.mobdrops.items.CustomItem;
import pl.mobdrops.mobs.MobDrop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DataHandler {

    private final MobDrops plugin = MobDrops.getInstance();
    private String prefix;

    public void loadConfig() {
        loadItems();
        loadMobs();
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(configFile);
        prefix = yml.getString("config.prefix");
    }

    public void loadItems() {
        plugin.getItemManager().getItems().clear();
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

    public void loadMobs() {
        plugin.getMobsManager().getMobs().clear();
        File mobFile = new File(plugin.getDataFolder(), "mobs.yml");
        if(!mobFile.exists()) {
            plugin.saveResource("mobs.yml", false);
        }
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(mobFile);
        ConfigurationSection section = yml.getConfigurationSection("mobs");
        if(section == null) return;
        for(String mob : section.getKeys(false)) {
            ConfigurationSection section1 = yml.getConfigurationSection("mobs." + mob);
            if(section1 == null) continue;
            List<MobDrop> dropList = new ArrayList<>();
            for(String drops : section1.getKeys(false)) {
                String itemID = yml.getString("mobs." + mob + "." + drops + ".itemID");
                double percentage = yml.getDouble("mobs." + mob + "." + drops + ".percentage");
                int countMin = yml.getInt("mobs." + mob + "." + drops + ".countMin");
                int countMax = yml.getInt("mobs." + mob + "." + drops + ".countMax");
                boolean firework = yml.getBoolean("mobs." + mob + "." + drops + ".firework");
                List<Integer> fireworkColor = yml.getIntegerList("mobs." + mob + "." + drops + ".fireworkColor");
                dropList.add(new MobDrop(itemID, percentage, countMin, countMax, firework, fireworkColor));
            }
            plugin.getMobsManager().getMobs().put(mob.toLowerCase(), dropList);
        }
    }

}
