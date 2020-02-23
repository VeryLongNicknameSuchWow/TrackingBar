package pl.rynbou.trackingbar;

import org.bukkit.plugin.java.JavaPlugin;
import pl.rynbou.trackingbar.cmds.TrackerCommand;
import pl.rynbou.trackingbar.settings.Settings;
import pl.rynbou.trackingbar.tracker.PlayerMoveListener;
import pl.rynbou.trackingbar.tracker.Tracker;
import pl.rynbou.trackingbar.tracker.TrackerRefreshTask;

public class TrackingBarMain extends JavaPlugin {

    private Settings settings;
    private Tracker tracker;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.settings = new Settings(this);
        this.tracker = new Tracker(this);

        getCommand("tracker").setExecutor(new TrackerCommand(this));

        if (settings.getTrackerRefreshRate() > 0)
            getServer().getScheduler().scheduleSyncRepeatingTask(this,
                    new TrackerRefreshTask(this),
                    settings.getTrackerRefreshRate(),
                    settings.getTrackerRefreshRate());
        else
            getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
    }

    @Override
    public void onDisable() {

    }

    public Settings getSettings() {
        return settings;
    }

    public Tracker getTracker() {
        return tracker;
    }
}
