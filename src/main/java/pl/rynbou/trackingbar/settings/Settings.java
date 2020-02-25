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
    private String bossBarFormatNoPlayersFound;
    private int trackerRange;
    private DimensionListType dimensionListType;
    private Set<World> dimensionList = new HashSet<>();
    private int trackerRefreshRate;
    private ItemStack trackerItem;
    private boolean craftable;

    private ShapedRecipe recipe;

    private String cycleMessage;
    private String toggleOnMessage;
    private String toggleOffMessage;
    private String blacklistedDimensionMessage;
    private String addFriendMessage;
    private String noPeopleToTrackMessage;

    public Settings(TrackingBarMain plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        FileConfiguration cfg = plugin.getConfig();

        bossBarFormat = cfg.getString("bossbar-format");
        bossBarFormatNoPlayersFound = cfg.getString("bossbar-format-no-players-found");
        trackerRange = cfg.getInt("tracker-range");
        dimensionListType = DimensionListType.valueOf(cfg.getString("dimension-list-type"));
        trackerRefreshRate = cfg.getInt("tracker-refresh-time");

        cycleMessage = StrUtil.color(cfg.getString("messages.cycle"));
        toggleOnMessage = StrUtil.color(cfg.getString("messages.toggle-on"));
        toggleOffMessage = StrUtil.color(cfg.getString("messages.toggle-off"));
        blacklistedDimensionMessage = StrUtil.color(cfg.getString("messages.blacklisted-dimension"));
        addFriendMessage = StrUtil.color(cfg.getString("messages.friend"));
        noPeopleToTrackMessage = StrUtil.color(cfg.getString("messages.no-players"));

        craftable = cfg.getBoolean("tracker-item.craftable");
        ConfigurationSection trackerSection = cfg.getConfigurationSection("tracker-item");
        trackerItem = ItemUtil.loadItemStack(trackerSection);

        if (craftable) {
            List<String> shape = trackerSection.getStringList("recipe.shape");

            recipe = new ShapedRecipe(NamespacedKey.minecraft("tracker"), trackerItem);

            recipe.shape(shape.stream().toArray(String[]::new));

            for (String s : trackerSection.getConfigurationSection("recipe.ingredients").getKeys(false)) {
                recipe.setIngredient(s.toCharArray()[0],
                        Material.getMaterial(trackerSection.getString("recipe.ingredients." + s)));
            }

            plugin.getServer().addRecipe(recipe);
        }

        for (String s : cfg.getStringList("dimenstion-list")) {
            dimensionList.add(Bukkit.getWorld(s));
        }
    }

    public String getBossBarFormat() {
        return bossBarFormat;
    }

    public String getBossBarFormatNoPlayersFound() {
        return bossBarFormatNoPlayersFound;
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

    public String getNoPeopleToTrackMessage() {
        return noPeopleToTrackMessage;
    }
}
