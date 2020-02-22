package pl.rynbou.trackingbar.tracker;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
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
        Player player = event.getPlayer();

        if (plugin.getTracker().getTrackerMap().containsKey(event.getPlayer())) {
            Player target = plugin.getTracker().getTrackerMap().get(player);
            int distance = (int) plugin.getTracker().distanceBetween(player, target);
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent("Tracking: " + target.getDisplayName() + " " + distance));
        }

        if (plugin.getTracker().getTrackerMap().containsValue(event.getPlayer())) {

        }
    }
}
