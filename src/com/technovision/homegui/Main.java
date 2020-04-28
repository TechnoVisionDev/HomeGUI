package com.technovision.homegui;

import com.technovision.homegui.commands.HomeCommand;
import com.technovision.homegui.gui.ChangeIconGUI;
import com.technovision.homegui.playerdata.PlayerDataReader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * HomeGUI Spigot Plugin
 * @author TechnoVision 4/26/2020
 * @version 1.1
 */
public class Main extends JavaPlugin {

    public static Main PLUGIN;
    public static PlayerDataReader dataReader;

    @Override
    public void onEnable() {
        PLUGIN = this;
        dataReader = new PlayerDataReader();
        loadConfig();

        //Commands
        getCommand(HomeCommand.HOME).setExecutor(new HomeCommand());
        getCommand(HomeCommand.H).setExecutor(new HomeCommand());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage( "[HomeGUI]: Plugin has been disabled.");
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        getConfig().options().copyHeader(true);
        saveConfig();
    }
}
