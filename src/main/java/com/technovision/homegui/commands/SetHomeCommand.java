package com.technovision.homegui.commands;

import com.cryptomorin.xseries.XMaterial;
import com.technovision.homegui.Homegui;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/*
Redstoneguy129
https://redstoneguy129.me/
07/05/2020
 */
public class SetHomeCommand implements CommandExecutor {

    public static final String SETHOME = "sethome";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // SetHome GUI
            if (cmd.getName().equalsIgnoreCase(SETHOME)) {
                if (args.length == 0) {
                    String title = Homegui.PLUGIN.getConfig().getString("gui-sethome-header").replace('&', '§');
                    title = title.replace("§8", "");
                    new AnvilGUI.Builder().onComplete((player1, text) -> {
                        player1.performCommand("essentials:sethome "+ text);
                        return AnvilGUI.Response.close();
                    }).preventClose().text("Change Me").item(XMaterial.GRASS_BLOCK.parseItem()).title(title).plugin(Homegui.PLUGIN).open(player);
                } else if (args.length == 1) {
                    player.performCommand("essentials:sethome " + args[0]);
                }
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
        }
        return true;
    }
}
