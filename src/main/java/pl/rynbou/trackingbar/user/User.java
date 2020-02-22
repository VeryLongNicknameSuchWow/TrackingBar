package pl.rynbou.trackingbar.user;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Set<Player> trackedBy = new HashSet<>();
    private Player tracking;
    private BossBar barInfo;
    private BossBar barCompass;

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
}
