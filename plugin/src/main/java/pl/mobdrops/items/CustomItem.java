package pl.mobdrops.items;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.mobdrops.MobDrops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class CustomItem {

    private final ItemStack itemStack;
    private final String id;
    private final Material item;
    private final String name;
    private final String lore;
    private final List<Enchantment> enchantments;
    private final List<String> stringEnchantments;
    private final boolean unbreakable;

    public CustomItem(String id, Material item, String name, String lore, List<String> enchantments, boolean unbreakable) {
        this.itemStack = new ItemStack(item);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        List<Enchantment> enchantmentList = new ArrayList<>();
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
            enchantmentList.add(ench);
            itemMeta.addEnchant(ench, level,true);
        }
        itemMeta.setUnbreakable(unbreakable);
        this.name = name;
        this.lore = lore;
        this.id = id;
        this.item = item;
        this.enchantments = enchantmentList;
        this.stringEnchantments = enchantments;
        this.unbreakable = unbreakable;
        this.itemStack.setItemMeta(itemMeta);
    }

}
