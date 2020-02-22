package pl.rynbou.trackingbar.tracker;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.rynbou.trackingbar.TrackingBarMain;
import pl.rynbou.trackingbar.user.User;

public class PlayerMoveListener implements Listener {

    private TrackingBarMain plugin;

    public PlayerMoveListener(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        User user = plugin.getTracker().getUser(player);

        Player tracking = user.getTracking();
        if (tracking != null) {
            int distance = (int) plugin.getTracker().distanceBetween(player, tracking);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent("Tracking: " + tracking.getDisplayName() + " " + distance));
        }

        user.getTrackedBy().forEach(trackedBy -> {
            int dist = (int) plugin.getTracker().distanceBetween(player, trackedBy);
            trackedBy.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent("Tracking: " + player.getDisplayName() + " " + dist));
        });
    }
}
