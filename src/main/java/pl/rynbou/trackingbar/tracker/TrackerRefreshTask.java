package pl.rynbou.trackingbar.tracker;

import org.bukkit.Bukkit;
import pl.rynbou.trackingbar.TrackingBarMain;
import pl.rynbou.trackingbar.user.User;

public class TrackerRefreshTask implements Runnable {

    TrackingBarMain plugin;

    public TrackerRefreshTask(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage("Refresh Task running");
        for (User user : plugin.getTracker().getUsers()) {
            if (user.getTracking() == null) continue;

            plugin.getTracker().refresh(user.getPlayer());
        }
    }
}
