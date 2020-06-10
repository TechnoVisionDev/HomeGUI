package com.technovision.homegui;

import com.technovision.homegui.commands.HomeCommand;
import com.technovision.homegui.commands.SetHomeCommand;
import com.technovision.homegui.events.HomeEvents;
import com.technovision.homegui.playerdata.PlayerDataReader;
import org.bukkit.plugin.java.JavaPlugin;

public class Homegui extends JavaPlugin {

    public static Homegui PLUGIN;
    public static PlayerDataReader dataReader;

    @Override
    public void onEnable() {
        PLUGIN = this;
        dataReader = new PlayerDataReader();
        loadConfig();

        //Events
        getServer().getPluginManager().registerEvents(new HomeEvents(), this);

        //Commands
        getCommand(SetHomeCommand.SETHOME).setExecutor(new SetHomeCommand()); //Redstoneguy129
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
