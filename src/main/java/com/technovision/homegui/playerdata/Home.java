package com.technovision.homegui.playerdata;

import org.bukkit.Material;

public class Home {

    private String name;
    private String world;
    private int x;
    private int y;
    private int z;
    private Material icon;

    public Home(String name, String world, int x, int y, int z, Material icon) {
        this.name = name;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.icon = icon;
    }

    public String getName() { return name; }

    public String getWorld() { return world; }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getZ() { return z; }

    public Material getIcon() {return icon;}

    public void setIcon(Material newIcon) {
        icon = newIcon;
    }
}
