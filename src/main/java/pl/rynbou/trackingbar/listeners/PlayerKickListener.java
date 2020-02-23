package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerKickListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerKickListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent event) {

    }
}
