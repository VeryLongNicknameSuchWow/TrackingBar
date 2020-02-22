package pl.rynbou.trackingbar.settings;

import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import pl.rynbou.trackingbar.TrackingBarMain;

import java.util.List;

public class Settings {

    private TrackingBarMain plugin;

    private String bossBarFormat;
    private int trackerRange;
    private DimensionListType dimensionListType;
    private List<World> dimensionList;
    private int trackerRefreshRate;
    private ItemStack trackerItem;
    private boolean craftable;
    private ShapedRecipe recipe;

    private String cycleMessage;
    private String toggleOnMessage;
    private String toggleOffMessage;
    private String blacklistedDimensionMessage;

    public Settings(TrackingBarMain plugin) {
        this.plugin = plugin;
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

    public List<World> getDimensionList() {
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
}
