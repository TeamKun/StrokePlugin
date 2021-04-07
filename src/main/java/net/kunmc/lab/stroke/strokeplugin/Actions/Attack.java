package net.kunmc.lab.stroke.strokeplugin.Actions;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Attack implements StrokeAction {
    private String name;
    private String stroke;
    private String announce;
    private String description;

    public Attack() {

    }

    public Attack(String name, String stroke, String announce, String description) {
        this.name = name;
        this.stroke = stroke;
        this.announce = announce;
        this.description = description;
    }

    @Override
    public void run(Player player) {
        player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SMALL_FIREBALL);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStroke() {
        return stroke;
    }

    @Override
    public String getAnnounce() {
        return announce;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
