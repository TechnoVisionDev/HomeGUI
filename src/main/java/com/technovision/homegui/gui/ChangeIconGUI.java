package com.technovision.homegui.gui;

import com.technovision.homegui.Homegui;
import com.technovision.homegui.playerdata.Home;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
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

    private ItemStack createGuiItem(final Material material, String name) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f" + name);
        meta.setLore(Arrays.asList("§7Click to select material."));
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
            Material item = Material.getMaterial(icon);
            if (item != null) {
                inv.addItem(createGuiItem(item, getFriendlyName(item)));
            }
        }
    }

    private String format(String s) {
        if (!s.contains("_")) {
            return capitalize(s);
        }
        String[] j = s.split("_");
        String c = "";
        for (String f : j) {
            f = capitalize(f);
            c += c.equalsIgnoreCase("") ? f : " " + f;
        }
        return c;
    }

    private String capitalize(String text) {
        String firstLetter = text.substring(0, 1).toUpperCase();
        String next = text.substring(1).toLowerCase();
        String capitalized = firstLetter + next;
        return capitalized;
    }

    public String getFriendlyName(Material m) {
        String name = format(m.name());
        return name;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
