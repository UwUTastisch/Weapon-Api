package net.theallay.weaponapi.listeners;

import net.theallay.weaponapi.weaponlib.WeaponInstance;
import net.theallay.weaponapi.weaponlib.WeaponLib;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnClickListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        //System.out.println("Es hat geklappt");
        ItemStack stack = event.getItem();
        if(stack == null) return;
        if(!stack.hasItemMeta()) return;
        try {
            WeaponInstance i = WeaponLib.
                    getWeapon(stack.getItemMeta().getCustomModelData()).
                    getWeaponInstance(event.getPlayer(), event.getPlayer().getInventory().getHeldItemSlot(),stack);
            i.shoot();
            System.out.println("Es hat geklappt");

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }


}
