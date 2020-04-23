package com.technovision.homegui.gui;

import com.technovision.homegui.Main;
import com.technovision.homegui.playerdata.Home;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.PLUGIN);
        inv = Bukkit.createInventory(this, 54, "Select Home Icon");
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

    @EventHandler
    public void onGuiActivation(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (activeGui.contains(player.getName()) && event.getCurrentItem() != null) {
            event.setCancelled(true);
            if (event.isLeftClick()) {
                String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
                String playerID = event.getWhoClicked().getUniqueId().toString();
                Material icon = event.getCurrentItem().getType();

                Main.dataReader.write(playerID, homes.get(playerID).getName(), icon);

                String message = "§a§l(!) §aSaved <name> home icon to " + ChatColor.UNDERLINE + itemName;
                player.sendMessage(message);
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onGuiClosing(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if (activeGui.contains(player.getName())) {
            activeGui.remove(player.getName());
        }
    }

    private void initItems() {
        inv.addItem(createGuiItem(Material.DIRT, "Dirt"));
        inv.addItem(createGuiItem(Material.STONE, "Stone"));
        inv.addItem(createGuiItem(Material.COBBLESTONE, "Cobblestone"));
        inv.addItem(createGuiItem(Material.OAK_LOG, "Oak Wood"));
        inv.addItem(createGuiItem(Material.ACACIA_LOG, "Acacia Log"));
        inv.addItem(createGuiItem(Material.CRAFTING_TABLE, "Crafting Table"));
        inv.addItem(createGuiItem(Material.PISTON, "Piston"));
        inv.addItem(createGuiItem(Material.NOTE_BLOCK, "Note Block"));
        inv.addItem(createGuiItem(Material.SPAWNER, "Spawner"));
        inv.addItem(createGuiItem(Material.BOOKSHELF, "Bookshelf"));
        inv.addItem(createGuiItem(Material.TNT, "TNT"));
        inv.addItem(createGuiItem(Material.SMOOTH_STONE_SLAB, "Stone Slab"));
        inv.addItem(createGuiItem(Material.ANVIL, "Anvil"));
        inv.addItem(createGuiItem(Material.IRON_BLOCK, "Block of Iron"));
        inv.addItem(createGuiItem(Material.GOLD_BLOCK, "Block of Gold"));
        inv.addItem(createGuiItem(Material.DIAMOND_BLOCK, "Block of Diamond"));
        inv.addItem(createGuiItem(Material.NETHER_BRICKS, "Nether Bricks"));
        inv.addItem(createGuiItem(Material.NETHERRACK, "Netherrack"));
        inv.addItem(createGuiItem(Material.END_STONE, "End Stone"));
        inv.addItem(createGuiItem(Material.CHEST, "Chest"));
        inv.addItem(createGuiItem(Material.ENDER_CHEST, "Ender Chest"));
        inv.addItem(createGuiItem(Material.STONE_BRICKS, "Stone Bricks"));
        inv.addItem(createGuiItem(Material.SNOW_BLOCK, "Snow"));
        inv.addItem(createGuiItem(Material.SOUL_SAND, "Soul Sand"));
        inv.addItem(createGuiItem(Material.QUARTZ_BLOCK, "Block of Quartz"));
        inv.addItem(createGuiItem(Material.DIAMOND_SWORD, "Diamond Sword"));
        inv.addItem(createGuiItem(Material.DIAMOND_AXE, "Diamond Axe"));
        inv.addItem(createGuiItem(Material.DIAMOND_PICKAXE, "Diamond Pickaxe"));
        inv.addItem(createGuiItem(Material.DIAMOND_SHOVEL, "Diamond Shovel"));
        inv.addItem(createGuiItem(Material.DIAMOND_HOE, "Diamond Hoe"));
        inv.addItem(createGuiItem(Material.ICE, "Ice"));
        inv.addItem(createGuiItem(Material.PACKED_ICE, "Packed Ice"));
        inv.addItem(createGuiItem(Material.DISPENSER, "Dispenser"));
        inv.addItem(createGuiItem(Material.FURNACE, "Furnace"));
        inv.addItem(createGuiItem(Material.HOPPER, "Hopper"));
        inv.addItem(createGuiItem(Material.GLASS, "Glass"));
        inv.addItem(createGuiItem(Material.SAND, "Sand"));
        inv.addItem(createGuiItem(Material.SPONGE, "Sponge"));
        inv.addItem(createGuiItem(Material.OBSIDIAN, "Obsidian"));
        inv.addItem(createGuiItem(Material.BEDROCK, "Bedrock"));
        inv.addItem(createGuiItem(Material.ENCHANTING_TABLE, "Enchantment Table"));
        inv.addItem(createGuiItem(Material.CACTUS, "Cactus"));
        inv.addItem(createGuiItem(Material.GLOWSTONE, "Glowstone"));
        inv.addItem(createGuiItem(Material.DIAMOND, "Diamond"));
        inv.addItem(createGuiItem(Material.EMERALD, "Emerald"));
        inv.addItem(createGuiItem(Material.GOLD_INGOT, "Gold Ingot"));
        inv.addItem(createGuiItem(Material.IRON_INGOT, "Iron Ingot"));
        inv.addItem(createGuiItem(Material.REDSTONE, "Redstone"));
        inv.addItem(createGuiItem(Material.BUCKET, "Bucket"));
        inv.addItem(createGuiItem(Material.LAVA_BUCKET, "Lava Bucket"));
        inv.addItem(createGuiItem(Material.WATER_BUCKET, "Water Bucket"));
        inv.addItem(createGuiItem(Material.GOLDEN_APPLE, "Golden Apple"));
        inv.addItem(createGuiItem(Material.EXPERIENCE_BOTTLE, "Bottle o' Enchanting"));
        inv.addItem(createGuiItem(Material.RED_BED, "Bed"));
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
