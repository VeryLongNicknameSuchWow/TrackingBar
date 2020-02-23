package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerJoinListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerJoinListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

    }
}
