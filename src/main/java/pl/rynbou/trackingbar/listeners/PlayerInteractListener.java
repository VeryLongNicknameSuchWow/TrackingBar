package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerInteractListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerInteractListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {

    }
}
