package net.kunmc.lab.stroke.strokeplugin.Actions;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Attack implements StrokeAction {
    @Override
    public void run(Player player, String stroke) {
        player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SMALL_FIREBALL);
    }

    @Override
    public String getName() {
        return "Attack";
    }

    @Override
    public String getStroke() {
        return "↑↓↑↓";
    }

    @Override
    public String getAnnounce() {
        return "Fire!";
    }
}
