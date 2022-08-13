package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.weaponlib.WeaponProvider;
import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
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
            WeaponInstance weaponInstance = WeaponProvider.
                    getWeapon(stack.getItemMeta().getCustomModelData()).
                    getWeaponInstance(
                            event.getPlayer(),
                            event.getPlayer().getInventory().getHeldItemSlot(),
                            stack
                    );
            weaponInstance.shoot();
            System.out.println("Es hat geklappt");

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }


}
