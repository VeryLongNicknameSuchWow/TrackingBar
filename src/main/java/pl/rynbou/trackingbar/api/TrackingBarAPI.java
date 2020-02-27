package pl.rynbou.trackingbar.api;

import org.bukkit.entity.Player;
import pl.rynbou.trackingbar.TrackingBarMain;

public class TrackingBarAPI {

    private TrackingBarMain plugin;

    public TrackingBarAPI(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    public void trackPlayer(Player player, Player toTrack) {
        plugin.getTracker().track(player, toTrack);
    }

    public void trackNearestPlayer(Player player) {
        plugin.getTracker().trackClosest(player);
    }

    public void trackNextPlayer(Player player) {
        plugin.getTracker().cycle(player);
    }

    public void toggleFriendStatus(Player player, Player friend) {
        plugin.getTracker().toggleFriend(player, friend);
    }

    public void disableTracker(Player player) {
        plugin.getTracker().disable(player);
    }
}
