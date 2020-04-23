package com.technovision.homegui.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

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

    public Material getIcon(String playerUUID, String homeName) {
        File dataFile = getFile(playerUUID);
        if (dataFile.exists()) {
            YamlConfiguration dataFileConfig = YamlConfiguration.loadConfiguration(dataFile);
            if (dataFileConfig.contains(homeName)) {
                String name = dataFileConfig.get(homeName).toString();
                return Material.valueOf(name);
            }
        }
        return Material.GRASS_BLOCK;
    }

    public void removeIcon(String playerUUID, String homeName) {
        try {
            File dataFile = getFile(playerUUID);
            if (dataFile.exists()) {
                YamlConfiguration dataFileConfig = YamlConfiguration.loadConfiguration(dataFile);
                System.out.println("HEYYYYYY " + homeName);
                dataFileConfig.set(homeName, null);
                dataFileConfig.save(dataFile);
            }
        } catch (IOException e) {
            Bukkit.broadcastMessage(ChatColor.RED + "File does not exist. Cannot load file.");
        }
    }
}
