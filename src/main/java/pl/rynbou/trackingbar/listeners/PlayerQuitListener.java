package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerQuitListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerQuitListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        plugin.getTracker().removeUser(event.getPlayer());
    }
}
