package com.technovision.homegui.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EssentialsReader {

    private YamlConfiguration fileReader;
    private List<Home> homes;

    private String world;
    private double x;
    private double y;
    private double z;

    public EssentialsReader(String playerID) {
        try {
            fileReader = new YamlConfiguration();
            File essentialsData = Bukkit.getServer().getPluginManager().getPlugin("Essentials").getDataFolder();
            File playerData = new File(essentialsData, File.separator + "userdata/" + playerID + ".yml");
            fileReader.load(playerData);
            initHomes();
        } catch (IOException | InvalidConfigurationException e) {
            System.out.println("[HomeGUI]: ERROR - Cannot find essentials data file!");
        }
        this.world = null;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    private void initHomes() {
        homes = new ArrayList<Home>();
        Set<String> names = fileReader.getConfigurationSection("homes").getKeys(false);
        for (String name : names) {
            Set<String> data = fileReader.getConfigurationSection("homes." + name).getKeys(false);
            for (String info : data) {
                if (info.matches("world")) {
                    world = fileReader.get("homes." + name + "." + info).toString();
                } else if (info.matches("x")) {
                    x = Double.parseDouble(fileReader.get("homes." + name + "." + info).toString());
                } else if (info.matches("y")) {
                    y = Double.parseDouble(fileReader.get("homes." + name + "." + info).toString());
                } else if (info.matches("z")) {
                    z = Double.parseDouble(fileReader.get("homes." + name + "." + info).toString());
                }
            }
            homes.add(new Home(name, world, (int) x, (int) y, (int) z, Material.GRASS_BLOCK));
        }
    }

    public List<Home> getHomes() {
        return homes;
    }
}
