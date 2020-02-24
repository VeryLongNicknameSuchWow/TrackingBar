package pl.rynbou.trackingbar.tracker;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import pl.rynbou.trackingbar.TrackingBarMain;
import pl.rynbou.trackingbar.user.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void refresh(Player player) {
        Player tracking = getUser(player).getTracking();
        if (tracking != null) {
            int distance = (int) plugin.getTracker().distanceBetween(player, tracking);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent("Tracking: " + tracking.getDisplayName() + " " + distance));
        }
    }

    public void refreshForOther(Player player) {
        for (Player p : getUser(player).getTrackedBy())
            refresh(p);
    }

    public void addFriend(Player player, Player friend) {
        getUser(player).getFriends().add(friend);
        player.sendMessage(plugin.getSettings().getAddFriendMessage()
                .replace("%player%", friend.getDisplayName()));
    }

    public void cycle(Player player) {
        //TODO
    }

    public void trackClosest(Player player) {
        //TODO
    }

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
