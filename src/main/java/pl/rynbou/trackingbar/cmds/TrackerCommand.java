package pl.rynbou.trackingbar.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.rynbou.trackingbar.TrackingBarMain;

public class TrackerCommand implements CommandExecutor {

    TrackingBarMain plugin;

    public TrackerCommand(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Correct usage: /tracker <name>");
        }

        Player player = (Player) sender;
        plugin.getTracker().startTracker(player, Bukkit.getPlayer(args[0]));
        return true;
    }
}
