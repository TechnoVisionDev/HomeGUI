package com.technovision.homegui.events;

import com.technovision.homegui.Homegui;
import com.technovision.homegui.gui.ChangeIconGUI;
import com.technovision.homegui.gui.HomeGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class HomeEvents implements Listener {

    @EventHandler
    public void onGuiActivation(InventoryClickEvent event){
        if (event.getInventory().getHolder() instanceof HomeGUI) {
            if (event.getClickedInventory() == null) { return; }
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() != null) {
                event.setCancelled(true);
                if (event.getClickedInventory().getType() == InventoryType.PLAYER) { return; }
                String playerID = player.getUniqueId().toString();
                int slotNum = event.getSlot();
                String name = HomeGUI.allHomes.get(playerID).get(slotNum).getName();
                //Left Click
                if (event.isLeftClick()) {
                    player.performCommand("essentials:home " + name);
                    player.closeInventory();
                    //Middle Click
                } else if (event.getClick() == ClickType.MIDDLE) {
                    player.performCommand("essentials:delhome " + name);
                    Homegui.dataReader.removeIcon(playerID, name);
                    player.closeInventory();
                    //Right Click
                } else if (event.isRightClick()) {
                    player.closeInventory();
                    ChangeIconGUI iconGUI = new ChangeIconGUI();
                    iconGUI.openInventory(player, HomeGUI.allHomes.get(playerID).get(slotNum));
                }
            }
        } else if (event.getInventory().getHolder() instanceof ChangeIconGUI) {
            if (event.getClickedInventory() == null) { return; }
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() != null) {
                event.setCancelled(true);
                if (event.getClickedInventory().getType() == InventoryType.PLAYER) { return; }
                if (event.isLeftClick()) {
                    String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
                    String playerID = event.getWhoClicked().getUniqueId().toString();
                    Material icon = event.getCurrentItem().getType();

                    String homeName = ChangeIconGUI.homes.get(playerID).getName();
                    Homegui.dataReader.write(playerID, homeName, icon);

                    String msg = Homegui.PLUGIN.getConfig().getString("icon-select-message").replace("&", "§");
                    msg = msg.replace("{home}", homeName);
                    msg = msg.replace("{icon}", itemName);
                    player.sendMessage(msg);
                    player.closeInventory();
                }
            }
        }
    }
}
