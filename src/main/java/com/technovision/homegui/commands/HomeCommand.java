package com.technovision.homegui.commands;

import com.technovision.homegui.Homegui;
import com.technovision.homegui.gui.HomeGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    public static final String HOME = "home";
    public static final String H = "h";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Home GUI
            if (cmd.getName().equalsIgnoreCase(HOME)) {
                if (args.length == 0) {
                    HomeGUI gui = new HomeGUI(player.getUniqueId());
                    gui.openInventory(player);
                } else if (args.length == 1) {
                    player.performCommand("essentials:home " + args[0]);
                }
            }

            // Reload
            else if (cmd.getName().equalsIgnoreCase(H)) {
                if (args.length == 0) {
                    player.performCommand("homegui:home");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (player.hasPermission("home.rl") || player.isOp()) {
                            Homegui.PLUGIN.reloadConfig();
                            sender.sendMessage("§7[§eHomeGUI§7]§f: Config file reloaded");
                        } else {
                            sender.sendMessage(ChatColor.RED + "You do not have permission to use that!");
                        }
                    }
                }
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
        }
        return true;
    }
}
