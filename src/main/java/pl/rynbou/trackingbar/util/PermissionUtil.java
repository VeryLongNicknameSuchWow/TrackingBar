package pl.rynbou.trackingbar.util;

import org.bukkit.entity.Player;

public class PermissionUtil {

    public static boolean hasPermission(Player player, String permission) {
        if (player.hasPermission("trackingbar." + permission))
            return true;
        else {
            player.sendMessage("You don't have permission");
            return false;
        }
    }
}
