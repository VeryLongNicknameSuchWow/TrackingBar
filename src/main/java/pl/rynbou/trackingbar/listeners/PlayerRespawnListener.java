package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerRespawnListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerRespawnListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {

    }
}
