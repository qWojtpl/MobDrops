package pl.mobdrops.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Events implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if(event.getEntity().getKiller() == null) return;

    }

}
