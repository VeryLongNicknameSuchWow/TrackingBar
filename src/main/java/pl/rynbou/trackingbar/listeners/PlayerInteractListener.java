package pl.rynbou.trackingbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.rynbou.trackingbar.TrackingBarMain;

public class PlayerInteractListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerInteractListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!event.getItem().isSimilar(plugin.getSettings().getTrackerItem())) return;
        if (event.getPlayer().isSneaking()) return;

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            plugin.getTracker().trackClosest(event.getPlayer());
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            plugin.getTracker().cycle(event.getPlayer());
        }

        event.setCancelled(true);
    }
}
