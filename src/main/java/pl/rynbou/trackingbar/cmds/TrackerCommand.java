package pl.rynbou.trackingbar.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.rynbou.trackingbar.TrackingBarMain;

public class TrackerCommand implements CommandExecutor {

    private TrackingBarMain plugin;

    public TrackerCommand(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            sendHelp(player);
            return true;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "next":
                    plugin.getTracker().cycle(player);
                    return true;
                case "nearest":
                    plugin.getTracker().trackClosest(player);
                    return true;
                case "disable":
                    plugin.getTracker().disable(player);
                    return true;
                case "friend":
                    player.sendMessage("Correct usage: /tracker friend <nickname>");
                    return true;
                case "debug":
                    plugin.getTracker().debug();
                    return true;
                default:
                    player.sendMessage("Incorrect argument \"" + args[0] + "\"");
                    sendHelp(player);
                    return true;
            }
        }

        if (args.length == 2) {
            switch (args[0]) {
                case "friend":
                    Player friend = Bukkit.getPlayer(args[1]);
                    if (friend == null) {
                        player.sendMessage("That player is not online!");
                        return true;
                    }
                    plugin.getTracker().toggleFriend(player, friend);
                    return true;
                default:
                    sendHelp(player);
                    return true;
            }
        }

        plugin.getTracker().track(player, Bukkit.getPlayer(args[0]));
        return true;
    }

    public void sendHelp(Player player) {
        player.sendMessage("Correct usage:");
        player.sendMessage("/tracker next    - Start tracking next player");
        player.sendMessage("/tracker nearest - Start tracking the nearest player");
        player.sendMessage("/tracker disable - Disable tracker");
        player.sendMessage("/tracker friend  - Toggle friendship status");
    }
}
