package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerTeleportListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerTeleportListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTeleportEvent(PlayerTeleportEvent event) {
        plugin.getTracker().removeUser(event.getPlayer());
    }
}
