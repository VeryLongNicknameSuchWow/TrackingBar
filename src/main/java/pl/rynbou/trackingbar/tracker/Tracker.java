package pl.rynbou.trackingbar.tracker;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import pl.rynbou.trackingbar.TrackingBarMain;
import pl.rynbou.trackingbar.settings.DimensionListType;
import pl.rynbou.trackingbar.user.User;
import pl.rynbou.trackingbar.util.AngleUtil;

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

    public void track(Player player, Player toTrack) {

        if (users.get(player) == null) {
            player.sendMessage(plugin.getSettings().getToggleOnMessage());
        }

        User user = getUser(player);
        User newPlayer = getUser(toTrack);

        if ((plugin.getSettings().getDimensionListType() == DimensionListType.BLACKLIST &&
                plugin.getSettings().getDimensionList().contains(player.getWorld())) ||
                (plugin.getSettings().getDimensionListType() == DimensionListType.WHITELIST &&
                        !plugin.getSettings().getDimensionList().contains(player.getWorld()))) {
            player.sendMessage(plugin.getSettings().getBlacklistedDimensionMessage());
            return;
        }

        if (user.getTracking() != null)
            getUser(user.getTracking()).getTrackedBy().remove(player);

        user.setTracking(toTrack);
        newPlayer.getTrackedBy().add(player);

        player.sendMessage(plugin.getSettings().getCycleMessage()
                .replace("%player%", toTrack.getDisplayName()));
    }

    public void refresh(Player player) {
        Player tracking = getUser(player).getTracking();
        if (tracking != null) {
            int distance = (int) plugin.getTracker().distanceBetween(player, tracking);
            User user = getUser(player);
            if (user.getBarInfo() == null) {
                //todo config
                BossBar bar = Bukkit.createBossBar(" ", BarColor.BLUE, BarStyle.SEGMENTED_20);
                bar.addPlayer(player);
                user.setBarInfo(bar);
            }

            if (user.getBarCompass() == null) {
                //todo config
                BossBar bar = Bukkit.createBossBar(" ", BarColor.BLUE, BarStyle.SEGMENTED_20);
                bar.addPlayer(player);
                user.setBarCompass(bar);
            }

            user.getBarInfo().setTitle(plugin.getSettings().getBossBarFormat()
                    .replace("%player%", tracking.getDisplayName())
                    .replace("%distance%", distance + ""));

            double angle = AngleUtil.calculateAngle(player.getLocation(), tracking.getLocation());
            String barMsg = AngleUtil.getBarMessage(angle);
            user.getBarCompass().setTitle(barMsg);
        }
    }

    public void refreshForOther(Player player) {
        for (Player p : getUser(player).getTrackedBy())
            refresh(p);
    }

    public void toggleFriend(Player player, Player friend) {
        User user = getUser(player);
        if (user.getFriends().contains(friend)) {
            user.getFriends().remove(friend);
            player.sendMessage(plugin.getSettings().getRemoveFriendMessage()
                    .replace("%player%", friend.getDisplayName()));
        } else {
            getUser(player).getFriends().add(friend);
            player.sendMessage(plugin.getSettings().getAddFriendMessage()
                    .replace("%player%", friend.getDisplayName()));
        }
    }

    public void cycle(Player player) {
        User user = getUser(player);
        Player trackedPlayer = user.getTracking();
        List<Player> closest = closestPlayers(player);
        int indexOfCurrent = closest.indexOf(trackedPlayer);
        if (indexOfCurrent < closest.size() - 1) {
            trackedPlayer = closest.get(closest.indexOf(trackedPlayer) + 1);
            track(player, trackedPlayer);
        } else {
            trackClosest(player);
        }
    }

    public void trackClosest(Player player) {
        User user = getUser(player);
        List<Player> closest = closestPlayers(player);
        if (closest.size() == 0) {
            player.sendMessage(plugin.getSettings().getNoPeopleToTrackMessage());
        } else if (!closest.get(0).equals(user.getTracking())) {
            track(player, closest.get(0));
        } else {
            player.sendMessage(plugin.getSettings().getToggleOffMessage());
            user.setTracking(null);
            clearBossBar(player);
        }
    }

    public List<Player> closestPlayers(Player player) {
        Map<Player, Double> map = new HashMap<>();
        int range = plugin.getSettings().getTrackerRange();
        User user = getUser(player);

        for (Player p : player.getWorld().getPlayers()) {
            if (p == player) continue;
            if (user.getFriends().contains(p)) continue;
            double distance = distanceBetween(player, p);
            if (distance < 0) continue;
            if (distance < range || range <= 0)
                map.put(p, distance);
        }

        return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public double distanceBetween(Player p1, Player p2) {
        if (!p1.getWorld().getPlayers().contains(p2)) {
            return -1;
        }
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

    public void removeUser(Player player) {
        User user = getUser(player);
        for (Player p : user.getTrackedBy()) {
            trackClosest(p);
        }
        getUser(user.getTracking()).getTrackedBy().remove(player);
        plugin.getTracker().getUsers().remove(user);
    }

    public void clearBossBar(Player player) {
        User user = getUser(player);
        if (user.getBarInfo() != null) {
            user.getBarInfo().removeAll();
            user.setBarInfo(null);
        }
        if (user.getBarCompass() != null) {
            user.getBarCompass().removeAll();
            user.setBarCompass(null);
        }
    }
}
