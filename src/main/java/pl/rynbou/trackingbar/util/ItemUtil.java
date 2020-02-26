package pl.rynbou.trackingbar.util;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static ItemStack loadItemStack(ConfigurationSection itemSection) {
        String materialString = itemSection.getString("type");
        if (materialString == null) {
            throw new RuntimeException("Invalid tracker-item material");
        }
        ItemStack itemStack;
        Material material = Material.getMaterial(materialString);
        if (material == null) {
            throw new RuntimeException("Invalid tracker-item material");
        }
        itemStack = new ItemStack(material);

        String name = itemSection.getString("name");
        if (name == null) {
            throw new RuntimeException("Invalid tracker-item name");
        }
        if (!name.isEmpty()) {
            String nameColor = StrUtil.color(name);
            ItemMeta im = itemStack.getItemMeta();
            if (im != null) {
                im.setDisplayName(nameColor);
                itemStack.setItemMeta(im);
            }
        }

        List<String> lore = itemSection.getStringList("lore");
        if (!lore.isEmpty()) {
            List<String> loreColor = new ArrayList<>();
            lore.forEach(s -> loreColor.add(StrUtil.color(s)));
            ItemMeta im = itemStack.getItemMeta();
            if (im != null) {
                im.setLore(loreColor);
                itemStack.setItemMeta(im);
            }
        }

        List<String> enchants = itemSection.getStringList("enchants");
        if (!enchants.isEmpty()) {
            ItemMeta im = itemStack.getItemMeta();
            if (im != null) {
                for (String s : enchants) {
                    String enchant = s.split(":")[0];
                    int power = Integer.parseInt(s.split(":")[1]);
                    Enchantment enchantment = EnchantmentWrapper.getByName(enchant);
                    if (enchantment == null)
                        throw new RuntimeException("Invalid tracker-item enchant: " + s);
                    im.addEnchant(enchantment, power, true);
                }
                itemStack.setItemMeta(im);
            }
        }

        return itemStack;
    }

}
