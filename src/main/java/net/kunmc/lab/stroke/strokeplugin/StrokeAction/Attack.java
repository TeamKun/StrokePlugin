package net.kunmc.lab.stroke.strokeplugin.StrokeAction;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Attack {
    public void Fire(Player player){
        player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SMALL_FIREBALL);
    }
}
