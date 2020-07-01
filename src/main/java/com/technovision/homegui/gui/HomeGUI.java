package com.technovision.homegui.gui;

import com.cryptomorin.xseries.XMaterial;
import com.technovision.homegui.Homegui;
import com.technovision.homegui.playerdata.EssentialsReader;
import com.technovision.homegui.playerdata.Home;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class HomeGUI implements InventoryHolder {

    public static Map<String, List<Home>> allHomes = new HashMap<>();

    private Inventory inv;
    private List<Home> homes;

    public HomeGUI(UUID playerUUID) {
        EssentialsReader reader = new EssentialsReader(playerUUID.toString());
        homes = reader.getHomes();
        String title = Homegui.PLUGIN.getConfig().getString("gui-main-header").replace('&', '§');
        title = title.replace("§8", "");
        inv = Bukkit.createInventory(this, calculateSize(), title);
        allHomes.put(playerUUID.toString(), homes);
        Homegui.dataReader.create(playerUUID.toString());
        initItems(playerUUID.toString());
    }

    private int calculateSize() {
        int size = homes.size();
        if (size == 0) {return 9;}
        if (size >= 54) { return 54; }
        if (size % 9 == 0) { return size; }
        int count = 0;
        for (int i = size%9; i>0; i--) {
            count++;
        }
        return (size - count) + 9;
    }

    private void initItems(String playerID) {
        String name;
        for (Home home : homes) {
            name = home.getName().substring(0, 1).toUpperCase() + home.getName().substring(1);
            ItemStack item = Homegui.dataReader.getItem(playerID, home.getName());
            String nameColor = Homegui.PLUGIN.getConfig().getString("home-color").replace("&", "§");
            name = nameColor + name;
            List<String> lore = Homegui.PLUGIN.getConfig().getStringList("home-lore");
            String location = "§f " + home.getX() + "x§7,§f " + home.getY() + "y§7,§f " + home.getZ() + "z";
            for (int i = 0; i < lore.size(); i++) {
                String newLine = lore.get(i).replace("{location}", location);
                newLine = newLine.replace("{world}", home.getWorld());
                if (newLine.contains("&")) {
                    newLine = newLine.replace("&", "§");
                } else {
                    newLine = "§f" + newLine;
                }
                lore.set(i, newLine);
            }
            inv.addItem(createGuiItem(item, name, lore));
        }
    }

    private ItemStack createGuiItem(final ItemStack item, final String name, final List<String> lore) {
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
