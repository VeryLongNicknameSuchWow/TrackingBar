package pl.rynbou.trackingbar.tracker;

import org.bukkit.entity.Player;
import pl.rynbou.trackingbar.TrackingBarMain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tracker {

    private TrackingBarMain plugin;

    private Map<Player, Player> trackerMap = new HashMap<>();

    public Tracker(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    public void startTracker(Player player) {
        trackerMap.put(player, closestPlayers(player).get(0));
    }

    public List<Player> closestPlayers(Player player) {
        Map<Player, Double> map = new HashMap<>();

        for (Player p : player.getWorld().getPlayers()) {
            double distance = distanceBetween(player, p);
            if (distance < plugin.getSettings().getTrackerRange())
                map.put(p, distance);
        }

        return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public double distanceBetween(Player p1, Player p2) {
        double deltaX = p1.getLocation().getX() - p2.getLocation().getX();
        double deltaZ = p1.getLocation().getZ() - p2.getLocation().getZ();

        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
    }
}
