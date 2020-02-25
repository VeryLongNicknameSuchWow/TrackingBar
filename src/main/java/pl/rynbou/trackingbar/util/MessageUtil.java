package pl.rynbou.trackingbar.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import pl.rynbou.trackingbar.TrackingBarMain;

public class MessageUtil {

    private TrackingBarMain plugin;

    public MessageUtil(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    public void sendCycleMessage(Player recipient, Player target) {
        send(recipient, plugin.getSettings().getCycleMessage()
                .replace("%player%", target.getDisplayName()));
    }

    public void sendClosestMessage(Player recipient, Player target) {
        send(recipient, plugin.getSettings().getClosestMessage()
                .replace("%player%", target.getDisplayName()));
    }

    public void sendToggleOnMessage(Player recipient) {
        send(recipient, plugin.getSettings().getToggleOnMessage());
    }

    public void sendToggleOffMessage(Player recipient) {
        send(recipient, plugin.getSettings().getToggleOffMessage());
    }

    public void sendBlacklistedDimensionMessage(Player recipient) {
        send(recipient, plugin.getSettings().getBlacklistedDimensionMessage());
    }

    public void sendFriendMessage(Player recipient, Player friend) {
        send(recipient, plugin.getSettings().getAddFriendMessage()
                .replace("%player%", friend.getDisplayName()));
    }

    public void sendUnfriendMessage(Player recipient, Player friend) {
        send(recipient, plugin.getSettings().getRemoveFriendMessage()
                .replace("%player%", friend.getDisplayName()));
    }

    public void sendNoPlayersMessage(Player recipient) {
        send(recipient, plugin.getSettings().getNoPeopleToTrackMessage());
    }

    public void send(Player player, String message) {
        message = StrUtil.color(message);

        switch (plugin.getSettings().getMessageLocation()) {
            case ACTION:
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                break;
            case CHAT:
                player.sendMessage(message);
                break;
        }
    }
}
