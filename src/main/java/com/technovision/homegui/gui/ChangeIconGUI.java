package com.technovision.homegui.gui;

import com.cryptomorin.xseries.XMaterial;
import com.technovision.homegui.Homegui;
import com.technovision.homegui.playerdata.Home;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ChangeIconGUI implements InventoryHolder, Listener {

    public static List<String> activeGui = new ArrayList<String>();
    public static Map<String, Home> homes = new HashMap<>();
    private Inventory inv;

    public ChangeIconGUI() {
        String title = Homegui.PLUGIN.getConfig().getString("gui-icon-header").replace('&', '§');
        title = title.replace("§8", "");
        inv = Bukkit.createInventory(this, 54, title);
        initItems();
    }

    private ItemStack addItemLore(ItemStack item) {
        final ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(Homegui.PLUGIN.getConfig().getString("icon-select-lore-message").replace('&', '§')));
        item.setItemMeta(meta);
        return item;
    }

    public void openInventory(Player player, Home home) {
        player.openInventory(inv);
        activeGui.add(player.getName());
        homes.put(player.getUniqueId().toString(), home);
    }

    private void initItems() {
        List<String> icons = Homegui.PLUGIN.getConfig().getStringList("icons");
        for (String icon : icons) {
            try {
                ItemStack item = XMaterial.valueOf(icon).parseItem();
                if (item != null) {
                    inv.addItem(addItemLore(item));
                }
            } catch (IllegalArgumentException e) {
                Homegui.PLUGIN.getLogger().config("ERROR: Incorrect or Missing Material Type in Config.yml!");
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
