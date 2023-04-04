package pl.mobdrops.events;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import pl.mobdrops.MobDrops;
import pl.mobdrops.mobs.MobDrop;
import pl.mobdrops.mobs.MobsManager;
import pl.mobdrops.util.RandomNumber;

public class Events implements Listener {

    private final MobDrops plugin = MobDrops.getInstance();
    private final MobsManager mobsManager = plugin.getMobsManager();

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if(event.getEntity().getKiller() == null) return;
        String entityType = event.getEntity().getType().name().toLowerCase();
        if(!mobsManager.getMobs().containsKey(entityType)) return;
        Location entityLocation = event.getEntity().getLocation();
        int mob_bonus = 1;
        Player killer = event.getEntity().getKiller();
        if(!killer.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            mob_bonus += killer.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
        }
        for(MobDrop mobDrop : mobsManager.getMobs().get(entityType.toLowerCase())) {
            int random = RandomNumber.randomInt(1, 1000000);
            double number = mobDrop.getPercentage() * 10000 * mob_bonus;
            if(random > number) continue;
            int random_count = RandomNumber.randomInt(mobDrop.getCountMin(), mobDrop.getCountMax());
            if(mobDrop.getCustomItem().getCommand() != null) {
                for(int i = 0; i < random_count; i++) {
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),
                            mobDrop.getCustomItem().getCommand().replace("%player%", killer.getName()));
                }
            } else {
                ItemStack itemStack = mobDrop.getCustomItem().getItemStack();
                itemStack.setAmount(random_count);
                entityLocation.getWorld().dropItem(entityLocation, itemStack);
            }
            if(mobDrop.isFirework()) {
                Firework fw = (Firework) entityLocation.getWorld().spawnEntity(entityLocation, EntityType.FIREWORK);
                FireworkMeta fwm = fw.getFireworkMeta();
                fwm.setPower(0);
                Color color = Color.fromRGB(
                        mobDrop.getFireworkColor().get(0), mobDrop.getFireworkColor().get(1), mobDrop.getFireworkColor().get(2));
                fwm.addEffect(FireworkEffect.builder().flicker(true).withColor(color).build());
                fw.setFireworkMeta(fwm);
                fw.detonate();
            }
        }
    }

}
