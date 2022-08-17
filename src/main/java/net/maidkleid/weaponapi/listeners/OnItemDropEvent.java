package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import net.maidkleid.weaponapi.weaponlib.WeaponProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class OnItemDropEvent implements Listener {

    @EventHandler
    public void onDropEvent(PlayerDropItemEvent event) {
        if (!event.getPlayer().isSneaking()) return;
        ItemStack stack = event.getItemDrop().getItemStack();
        if(!stack.hasItemMeta()) return;
        Player player = event.getPlayer();
        try {
            WeaponInstance weaponInstance = WeaponProvider.getWeaponInstance(
                    stack,
                    player,
                    player.getInventory().getHeldItemSlot());
            if(weaponInstance != null) {
                weaponInstance.tryStartReload();
                event.setCancelled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
