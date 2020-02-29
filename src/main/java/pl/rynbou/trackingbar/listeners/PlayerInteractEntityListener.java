package pl.rynbou.trackingbar.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import pl.rynbou.trackingbar.TrackingBarMain;
import pl.rynbou.trackingbar.util.PermissionUtil;

public class PlayerInteractEntityListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerInteractEntityListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        if (!event.getPlayer().getInventory().getItemInMainHand().isSimilar(plugin.getSettings().getTrackerItem()))
            return;
        if (!event.getPlayer().isSneaking()) return;
        if (!(event.getRightClicked() instanceof Player)) return;
        if (!(PermissionUtil.hasPermission(event.getPlayer(), "item"))) return;

        Player friend = (Player) event.getRightClicked();
        plugin.getTracker().toggleFriend(event.getPlayer(), friend);

        event.setCancelled(true);
    }
}
