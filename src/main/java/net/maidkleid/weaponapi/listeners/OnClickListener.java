package net.maidkleid.weaponapi.listeners;

import net.maidkleid.weaponapi.weaponlib.WeaponProvider;
import net.maidkleid.weaponapi.weaponlib.WeaponInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class OnClickListener implements Listener {

    private static final HashSet<Action> actions = new HashSet<>();

    static {
        actions.addAll(List.of(/*Action.RIGHT_CLICK_AIR,*/Action.RIGHT_CLICK_BLOCK,Action.LEFT_CLICK_BLOCK));
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        //System.out.println("Es hat geklappt");
        Action action = event.getAction();
        if (!actions.contains(action)) return;

        //event.getPlayer().sendMessage("Shooting while Action: " + action + event.get//event.getPlayer().getInventory().getItem(Objects.requireNonNull(event.getHand())));
        ItemStack stack = event.getItem();
        if(stack == null) return;
        if(!stack.hasItemMeta()) return;
        Player player = event.getPlayer();
        try {
            WeaponInstance weaponInstance = WeaponProvider.getWeaponInstance(
                    stack,
                    player,
                    player.getInventory().getHeldItemSlot());
            assert weaponInstance != null;
            weaponInstance.doShoot();
            if(event.isBlockInHand()) event.setCancelled(true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }


}
