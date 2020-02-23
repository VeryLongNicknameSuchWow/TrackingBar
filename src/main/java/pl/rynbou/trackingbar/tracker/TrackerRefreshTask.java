package pl.rynbou.trackingbar.tracker;

import pl.rynbou.trackingbar.TrackingBarMain;
import pl.rynbou.trackingbar.user.User;

public class TrackerRefreshTask implements Runnable {

    TrackingBarMain plugin;

    public TrackerRefreshTask(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (User user : plugin.getTracker().getUsers()) {
            if (user.getTracking() == null) continue;

            plugin.getTracker().refresh(user.getPlayer());
        }
    }
}
