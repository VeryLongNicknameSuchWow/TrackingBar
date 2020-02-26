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
        String itemString = itemSection.getString("type");
        if (itemString == null) {
            return null;
        }
        ItemStack itemStack;
        Material material = Material.getMaterial(itemString);
        if (material == null) {
            return null;
        }
        itemStack = new ItemStack(material);

        String name = itemSection.getString("name");
        if (name != null && !name.isEmpty()) {
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
            for (String s : enchants) {
                String enchant = s.split(":")[0];
                int power = Integer.parseInt(s.split(":")[1]);
                Enchantment enchantment = EnchantmentWrapper.getByName(enchant.toLowerCase());
                if (enchantment != null)
                    itemStack.addEnchantment(enchantment, power);
            }
        }

        return itemStack;
    }

}
