package pl.rynbou.trackingbar.tracker;

import org.bukkit.entity.Player;
import pl.rynbou.trackingbar.TrackingBarMain;
import pl.rynbou.trackingbar.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class Tracker {

    private TrackingBarMain plugin;

    private Map<Player, User> users = new HashMap<>();

    public Tracker(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    public void startTracker(Player player, Player tracking) {
        User user = getUser(player);
        User trackingUser = getUser(tracking);

        user.setTracking(tracking);
        trackingUser.getTrackedBy().add(player);
    }

//    public void startTracker(Player player) {
//        List<Player> closestPlayers = closestPlayers(player);
//        if (closestPlayers.size() > 0)
//            trackerMap.put(player, closestPlayers(player).get(0));
//        else
//            trackerMap.put(player, null);
//    }

    public List<Player> closestPlayers(Player player) {
        Map<Player, Double> map = new HashMap<>();

        for (Player p : player.getWorld().getPlayers()) {
            if (p == player) continue;
            double distance = distanceBetween(player, p);
            //TODO fix range check
//            if (distance < plugin.getSettings().getTrackerRange())
            map.put(p, distance);
        }

        return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public double distanceBetween(Player p1, Player p2) {
        double deltaX = p1.getLocation().getX() - p2.getLocation().getX();
        double deltaZ = p1.getLocation().getZ() - p2.getLocation().getZ();

        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public User getUser(Player player) {
        User user = users.get(player);
        if (user == null) {
            user = new User(player);
            users.put(player, user);
        }
        return user;
    }

}
