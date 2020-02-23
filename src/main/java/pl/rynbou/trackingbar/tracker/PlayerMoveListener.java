package pl.rynbou.trackingbar.tracker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerMoveListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerMoveListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        plugin.getTracker().refresh(event.getPlayer());
        plugin.getTracker().refreshForOther(event.getPlayer());
    }
}
