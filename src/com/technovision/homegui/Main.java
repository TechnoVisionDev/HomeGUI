package com.technovision.homegui;

import com.technovision.homegui.commands.HomeCommand;
import com.technovision.homegui.gui.ChangeIconGUI;
import com.technovision.homegui.playerdata.PlayerDataReader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class.
 * @author TechnoVision 4/21/2020
 */
public class Main extends JavaPlugin {

    public static Main PLUGIN;
    public static ChangeIconGUI iconGUI;
    public static PlayerDataReader dataReader;

    @Override
    public void onEnable() {
        PLUGIN = this;
        iconGUI = new ChangeIconGUI();
        dataReader = new PlayerDataReader();

        //Commands
        getCommand(HomeCommand.HOME).setExecutor(new HomeCommand());
        getServer().getConsoleSender().sendMessage("[HomeGUI]: Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage( "[HomeGUI]: Plugin is disabled!");
    }
}
