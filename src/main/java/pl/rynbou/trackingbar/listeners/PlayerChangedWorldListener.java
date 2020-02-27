package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerChangedWorldListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerChangedWorldListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        plugin.getTracker().removeUser(event.getPlayer());
    }
}
