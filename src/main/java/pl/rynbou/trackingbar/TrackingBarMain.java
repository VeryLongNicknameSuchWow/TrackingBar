package pl.rynbou.trackingbar;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.rynbou.trackingbar.api.TrackingBarAPI;
import pl.rynbou.trackingbar.cmds.TrackerCommand;
import pl.rynbou.trackingbar.listeners.*;
import pl.rynbou.trackingbar.settings.Settings;
import pl.rynbou.trackingbar.tracker.PlayerMoveListener;
import pl.rynbou.trackingbar.tracker.Tracker;
import pl.rynbou.trackingbar.tracker.TrackerRefreshTask;
import pl.rynbou.trackingbar.user.User;
import pl.rynbou.trackingbar.util.MessageUtil;

public class TrackingBarMain extends JavaPlugin {

    private Settings settings;
    private Tracker tracker;
    private MessageUtil messages;
    private TrackingBarAPI api;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.settings = new Settings(this);
        boolean success = this.settings.loadConfig();
        if (!success) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.tracker = new Tracker(this);
        this.messages = new MessageUtil(this);
        this.api = new TrackingBarAPI(this);

        register("tracker", new TrackerCommand(this));
        register(new PlayerJoinListener(this));
        register(new PlayerKickListener(this));
        register(new PlayerQuitListener(this));
        register(new PlayerRespawnListener(this));
        register(new PlayerTeleportListener(this));
        register(new PlayerChangedWorldListener(this));

        if (settings.isItemEnabled()) {
            register(new PlayerInteractEntityListener(this));
            register(new PlayerInteractListener(this));
        }


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
        if (tracker != null && tracker.getUsers() != null)
            for (User user : getTracker().getUsers()) {
                if (user.getBarCompass() != null)
                    user.getBarCompass().removeAll();
                if (user.getBarInfo() != null)
                    user.getBarInfo().removeAll();
            }
    }

    private void register(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void register(String name, CommandExecutor executor) {
        PluginCommand command = getCommand(name);
        if (command == null) {
            getServer().getLogger().warning("Could not register command: " + name);
            return;
        }
        command.setExecutor(executor);
    }

    public Settings getSettings() {
        return settings;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public MessageUtil getMessages() {
        return messages;
    }

    public TrackingBarAPI getAPI() {
        return api;
    }
}
