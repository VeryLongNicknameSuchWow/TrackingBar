package pl.rynbou.trackingbar.user;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

    private Player player;
    private Set<Player> trackedBy = new HashSet<>();
    private Player tracking;
    private BossBar barInfo;
    private BossBar barCompass;

    public User(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<Player> getTrackedBy() {
        return trackedBy;
    }

    public void setTrackedBy(Set<Player> trackedBy) {
        this.trackedBy = trackedBy;
    }

    public Player getTracking() {
        return tracking;
    }

    public void setTracking(Player tracking) {
        this.tracking = tracking;
    }

    public BossBar getBarInfo() {
        return barInfo;
    }

    public void setBarInfo(BossBar barInfo) {
        this.barInfo = barInfo;
    }

    public BossBar getBarCompass() {
        return barCompass;
    }

    public void setBarCompass(BossBar barCompass) {
        this.barCompass = barCompass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(player, user.player);
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }
}
