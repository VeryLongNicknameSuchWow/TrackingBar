package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerPortalListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerPortalListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent event) {
        plugin.getTracker().removeUser(event.getPlayer());
    }
}
