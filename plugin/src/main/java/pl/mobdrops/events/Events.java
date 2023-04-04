package pl.mobdrops.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import pl.mobdrops.MobDrops;
import pl.mobdrops.mobs.MobsManager;

public class Events implements Listener {

    private final MobDrops plugin = MobDrops.getInstance();
    private final MobsManager mobsManager = plugin.getMobsManager();

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if(event.getEntity().getKiller() == null) return;
        if(!mobsManager.getMobs().containsKey(event.getEntity().getType().name().toLowerCase())) return;
    }

}
