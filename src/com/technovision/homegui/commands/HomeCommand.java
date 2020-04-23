package com.technovision.homegui.commands;

import com.technovision.homegui.gui.HomeGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    public static final String HOME = "home";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase(HOME)) {
                if (args.length == 0) {
                    HomeGUI gui = new HomeGUI(player.getUniqueId());
                    gui.openInventory(player);
                } else if (args.length == 1) {
                    player.performCommand("essentials:home " + args[0]);
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
        }
        return true;
    }
}
