package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerInteractEntityListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerInteractEntityListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {

    }
}
