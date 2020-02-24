package pl.rynbou.trackingbar;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.rynbou.trackingbar.cmds.TrackerCommand;
import pl.rynbou.trackingbar.listeners.*;
import pl.rynbou.trackingbar.settings.Settings;
import pl.rynbou.trackingbar.tracker.PlayerMoveListener;
import pl.rynbou.trackingbar.tracker.Tracker;
import pl.rynbou.trackingbar.tracker.TrackerRefreshTask;
import pl.rynbou.trackingbar.user.User;

public class TrackingBarMain extends JavaPlugin {

    private Settings settings;
    private Tracker tracker;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.settings = new Settings(this);
        this.settings.loadConfig();
        this.tracker = new Tracker(this);

        register("tracker", new TrackerCommand(this));
        register(new PlayerInteractEntityListener(this));
        register(new PlayerInteractListener(this));
        register(new PlayerJoinListener(this));
        register(new PlayerKickListener(this));
        register(new PlayerQuitListener(this));
        register(new PlayerRespawnListener(this));
        register(new PlayerTeleportListener(this));

        int refresh = settings.getTrackerRefreshRate();
        if (refresh > 0) {
            getServer().getScheduler().scheduleSyncRepeatingTask(
                    this,
                    new TrackerRefreshTask(this),
                    refresh,
                    refresh);
        } else {
            register(new PlayerMoveListener(this));
        }
    }

    @Override
    public void onDisable() {
        for (User user : getTracker().getUsers()) {
            user.getBarCompass().removeAll();
            user.getBarInfo().removeAll();
        }
    }

    private void register(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void register(String command, CommandExecutor executor) {
        getCommand(command).setExecutor(executor);
    }

    public Settings getSettings() {
        return settings;
    }

    public Tracker getTracker() {
        return tracker;
    }
}
