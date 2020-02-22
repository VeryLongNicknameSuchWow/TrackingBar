package pl.rynbou.trackingbar.util;

import net.md_5.bungee.api.ChatColor;

public class StrUtil {

    public static String color(String str) {
        if (str == null) return "";
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
