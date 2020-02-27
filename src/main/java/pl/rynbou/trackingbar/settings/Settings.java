package pl.rynbou.trackingbar.settings;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import pl.rynbou.trackingbar.TrackingBarMain;
import pl.rynbou.trackingbar.util.ItemUtil;
import pl.rynbou.trackingbar.util.StrUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Settings {

    private TrackingBarMain plugin;

    private String bossBarFormat;
    private int trackerRange;
    private DimensionListType dimensionListType;
    private Set<World> dimensionList = new HashSet<>();
    private int trackerRefreshRate;
    private ItemStack trackerItem;
    private boolean craftable;
    private boolean item;
    private MessageLocation messageLocation;

    private ShapedRecipe recipe;

    private String cycleMessage;
    private String closestMessage;
    private String toggleOnMessage;
    private String toggleOffMessage;
    private String blacklistedDimensionMessage;
    private String addFriendMessage;
    private String removeFriendMessage;
    private String noPeopleToTrackMessage;

    public Settings(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    public boolean loadConfig() {
        dimensionList = new HashSet<>();
        FileConfiguration cfg = plugin.getConfig();

        bossBarFormat = cfg.getString("bossbar-format");
        if (bossBarFormat == null) {
            plugin.getServer().getLogger().warning("Invalid bossbar-format");
            return false;
        }

        trackerRange = cfg.getInt("tracker-range");
        if (trackerRange < 0) {
            plugin.getServer().getLogger().warning("Invalid tracker-range");
            return false;
        }

        try {
            dimensionListType = DimensionListType.valueOf(cfg.getString("dimension-list-type"));
        } catch (IllegalArgumentException e) {
            plugin.getServer().getLogger().warning("Invalid dimension-list-type");
            e.printStackTrace();
            return false;
        }

        trackerRefreshRate = cfg.getInt("tracker-refresh-time");
        if (trackerRefreshRate < 0) {
            plugin.getServer().getLogger().warning("Invalid tracker-refresh-time");
            return false;
        }

        try {
            messageLocation = MessageLocation.valueOf(cfg.getString("messages-type"));
        } catch (IllegalArgumentException e) {
            plugin.getServer().getLogger().warning("Invalid messages-type");
            e.printStackTrace();
            return false;
        }

        cycleMessage = StrUtil.color(cfg.getString("messages.cycle"));
        if (cycleMessage.equals("")) {
            plugin.getServer().getLogger().warning("Empty/invalid cycle message");
            return false;
        }

        closestMessage = StrUtil.color(cfg.getString("messages.closest"));
        if (cycleMessage.equals("")) {
            plugin.getServer().getLogger().warning("Empty/invalid closest message");
            return false;
        }

        toggleOnMessage = StrUtil.color(cfg.getString("messages.toggle-on"));
        if (toggleOnMessage.equals("")) {
            plugin.getServer().getLogger().warning("Empty/invalid toggle-on message");
            return false;
        }

        toggleOffMessage = StrUtil.color(cfg.getString("messages.toggle-off"));
        if (toggleOnMessage.equals("")) {
            plugin.getServer().getLogger().warning("Empty/invalid toggle-off message");
            return false;
        }

        blacklistedDimensionMessage = StrUtil.color(cfg.getString("messages.blacklisted-dimension"));
        if (blacklistedDimensionMessage.equals("")) {
            plugin.getServer().getLogger().warning("Empty/invalid blacklisted-dimension message");
            return false;
        }

        addFriendMessage = StrUtil.color(cfg.getString("messages.friend"));
        if (addFriendMessage.equals("")) {
            plugin.getServer().getLogger().warning("Empty/invalid closest friend message");
            return false;
        }

        removeFriendMessage = StrUtil.color(cfg.getString("messages.unfriend"));
        if (addFriendMessage.equals("")) {
            plugin.getServer().getLogger().warning("Empty/invalid closest unfriend message");
            return false;
        }

        noPeopleToTrackMessage = StrUtil.color(cfg.getString("messages.no-players"));
        if (addFriendMessage.equals("")) {
            plugin.getServer().getLogger().warning("Empty/invalid closest no-players message");
            return false;
        }

        for (String s : cfg.getStringList("dimension-list")) {
            World world = Bukkit.getWorld(s);
            if (world == null) {
                plugin.getServer().getLogger().warning("Invalid world: " + s);
                return false;
            }
            dimensionList.add(world);
        }

        item = cfg.getBoolean("enable-item");
        if (!item) return true;

        ConfigurationSection trackerSection = cfg.getConfigurationSection("tracker-item");
        if (trackerSection == null) {
            plugin.getServer().getLogger().warning("Missing tracker-item section");
            return false;
        }

        try {
            trackerItem = ItemUtil.loadItemStack(trackerSection);
        } catch (Exception e) {
            plugin.getServer().getLogger().warning("Invalid tracker-item section");
            e.printStackTrace();
            return false;
        }

        craftable = cfg.getBoolean("tracker-item.craftable");
        if (!craftable) return true;

        List<String> shape = trackerSection.getStringList("recipe.shape");
        recipe = new ShapedRecipe(NamespacedKey.minecraft("tracker"), trackerItem);

        recipe.shape(shape.stream().toArray(String[]::new));

        ConfigurationSection ingredients = trackerSection.getConfigurationSection("recipe.ingredients");
        if (ingredients == null) {
            plugin.getServer().getLogger().warning("Missing ingredients section");
            return false;
        }

        for (String s : ingredients.getKeys(false)) {
            String materialString = trackerSection.getString("recipe.ingredients." + s);
            if (materialString == null) {
                plugin.getServer().getLogger().warning("Invalid material in ingredients section");
                return false;
            }

            Material material = Material.getMaterial(materialString);
            if (material == null) {
                plugin.getServer().getLogger().warning("Invalid material in ingredients section");
                return false;
            }
            recipe.setIngredient(s.toCharArray()[0], material);
        }

        plugin.getServer().addRecipe(recipe);

        return true;
    }

    public String getBossBarFormat() {
        return bossBarFormat;
    }

    public int getTrackerRange() {
        return trackerRange;
    }

    public DimensionListType getDimensionListType() {
        return dimensionListType;
    }

    public Set<World> getDimensionList() {
        return dimensionList;
    }

    public int getTrackerRefreshRate() {
        return trackerRefreshRate;
    }

    public ItemStack getTrackerItem() {
        return trackerItem;
    }

    public boolean isCraftable() {
        return craftable;
    }

    public ShapedRecipe getRecipe() {
        return recipe;
    }

    public String getCycleMessage() {
        return cycleMessage;
    }

    public String getToggleOnMessage() {
        return toggleOnMessage;
    }

    public String getToggleOffMessage() {
        return toggleOffMessage;
    }

    public String getBlacklistedDimensionMessage() {
        return blacklistedDimensionMessage;
    }

    public String getAddFriendMessage() {
        return addFriendMessage;
    }

    public String getRemoveFriendMessage() {
        return removeFriendMessage;
    }

    public String getNoPeopleToTrackMessage() {
        return noPeopleToTrackMessage;
    }

    public String getClosestMessage() {
        return closestMessage;
    }

    public MessageLocation getMessageLocation() {
        return messageLocation;
    }

    public boolean isItemEnabled() {
        return item;
    }
}
