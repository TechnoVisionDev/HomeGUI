package com.technovision.homegui.playerdata;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerDataReader {

    private static final String dir = "plugins/HomeGUI/userdata/";
    private static Map<String, File> playerFiles;


    public PlayerDataReader() {
        File data = new File(dir);
        data.mkdirs();
        playerFiles = new HashMap<>();
    }

    public File getFile(String playerUUID) {
        return playerFiles.get(playerUUID);
    }

    public void create(String playerUUID) {
        try {
            File dataFile = new File(dir, playerUUID + ".yml");
            dataFile.createNewFile();
            playerFiles.put(playerUUID, dataFile);
        } catch (IOException e) {
            Bukkit.broadcastMessage(ChatColor.RED + "File does not exist. Cannot load file.");
        }
    }

    public void write(String playerUUID, String key, Material icon) {
        try {
            File dataFile = getFile(playerUUID);
            YamlConfiguration dataFileConfig = YamlConfiguration.loadConfiguration(dataFile);
            dataFileConfig.set(key, icon.toString());
            dataFileConfig.save(dataFile);
        } catch (IOException e) {
            Bukkit.broadcastMessage(ChatColor.RED + "File does not exist. Cannot load file.");
        }
    }

    public ItemStack getItem(String playerUUID, String homeName) {
        File dataFile = getFile(playerUUID);
        if (dataFile.exists()) {
            YamlConfiguration dataFileConfig = YamlConfiguration.loadConfiguration(dataFile);
            if (dataFileConfig.contains(homeName)) {
                String name = dataFileConfig.get(homeName).toString();
                XMaterial item;
                String materialName = Material.getMaterial(name).toString();
                if (materialName.equalsIgnoreCase("PISTON_MOVING_PIECE")) {
                    item = XMaterial.PISTON;
                } else if (materialName.equalsIgnoreCase("BED")) {
                    item = XMaterial.RED_BED;
                } else {
                    item = XMaterial.matchXMaterial(Material.getMaterial(name));
                }
                return item.parseItem();
            }
        }
        return XMaterial.GRASS_BLOCK.parseItem();
    }

    public void removeIcon(String playerUUID, String homeName) {
        try {
            File dataFile = getFile(playerUUID);
            if (dataFile.exists()) {
                YamlConfiguration dataFileConfig = YamlConfiguration.loadConfiguration(dataFile);
                dataFileConfig.set(homeName, null);
                dataFileConfig.save(dataFile);
            }
        } catch (IOException e) {
            Bukkit.broadcastMessage(ChatColor.RED + "File does not exist. Cannot load file.");
        }
    }
}
