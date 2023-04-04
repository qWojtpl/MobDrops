package pl.mobdrops.items;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.mobdrops.MobDrops;

import java.util.Arrays;
import java.util.List;

@Getter
public class CustomItem {

    private final ItemStack itemStack;

    public CustomItem(String id, Material item, String name, String lore, List<String> enchantments, boolean unbreakable) {
        this.itemStack = new ItemStack(item);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if(name != null) {
            itemMeta.setDisplayName(name.replace("&", "§"));
        }
        if(lore != null) {
            itemMeta.setLore(Arrays.asList(lore.replace("&", "§").split("%nl%")));
        }
        for(String strEnch : enchantments) {
            String[] enchSplit = strEnch.split(":");
            int level;
            try {
                level = Integer.parseInt(enchSplit[1]);
            } catch(NumberFormatException e) {
                MobDrops.getInstance().getLogger().severe("Cannot compare " + enchSplit[1]
                        + " with a correct enchantment level! (Reading " + id + ")");
                level = 1;
            }
            Enchantment ench = Enchantment.getByName(enchSplit[0]);
            if(ench == null) {
                MobDrops.getInstance().getLogger().severe("Cannot compare " + enchSplit[0]
                        + " with a correct enchantment! (Reading " + id + ")");
                ench = Enchantment.DAMAGE_ALL;
            }
            itemMeta.addEnchant(ench, level,true);
        }
        itemMeta.setUnbreakable(unbreakable);
        this.itemStack.setItemMeta(itemMeta);
    }

}
