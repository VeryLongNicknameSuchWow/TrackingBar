package pl.rynbou.trackingbar;

import org.bukkit.plugin.java.JavaPlugin;
import pl.rynbou.trackingbar.settings.Settings;
import pl.rynbou.trackingbar.tracker.Tracker;

public class TrackingBarMain extends JavaPlugin {

    private Settings settings;
    private Tracker tracker;

    @Override
    public void onEnable() {
        this.settings = new Settings(this);
        this.tracker = new Tracker(this);
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
