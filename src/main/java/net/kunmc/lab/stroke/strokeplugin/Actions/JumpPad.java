package net.kunmc.lab.stroke.strokeplugin.Actions;

import net.kunmc.lab.stroke.strokeplugin.Config;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction;
import net.kunmc.lab.stroke.strokeplugin.StrokePlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class JumpPad implements Listener, StrokeAction {
    private Player player;
    private boolean count = false;
    private final int[] cautionTitle = {5, 20, 5};
    private String name;
    private String stroke;
    private String announce;
    private String description;

    public JumpPad() {

    }

    public JumpPad(String name, String stroke, String announce, String description) {
        this.name = name;
        this.stroke = stroke;
        this.announce = announce;
        this.description = description;
    }

    @Override
    public void run(Player player, String stroke) {
        Entity ball = player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SNOWBALL);
        ball.setVelocity(player.getLocation().getDirection());
        player.sendTitle(Config.getJumpPadAnnounce(), ChatColor.AQUA + stroke, cautionTitle[0], cautionTitle[1], cautionTitle[2]);
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

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntity().getType().toString().equalsIgnoreCase(Config.getRod())) {
            event.getEntity().remove();
        }
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        player = event.getPlayer();
        String item = player.getInventory().getItemInMainHand().getType().toString();

        if (event.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_AIR")
                || event.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_BLOCK")
        ) {
            if (item.equalsIgnoreCase(Config.getRod())) {
                count = true;
            }
        }
    }

    @EventHandler
    public void BallBreak(ProjectileHitEvent event) {
        Block block;

        if (event.getEntity().getType().toString().equalsIgnoreCase("SNOWBALL") && count) {
            block = event.getEntity().getLocation().getBlock();
            block.setType(Material.PRISMARINE_SLAB);

            BukkitRunnable task = new BukkitRunnable() {
                public void run() {
                    block.setType(Material.AIR);
                    this.cancel();
                    return;
                }
            };
            task.runTaskTimer(StrokePlugin.getPlugin(), 100L, 0L);
        }
        count = false;
    }

    @EventHandler
    public void jumppad(PlayerMoveEvent event) {
        if ((event.getPlayer()).isOnGround()) {
            Location loc = event.getTo();
            Location p = loc.clone();
            p.add(0, -0.1, 0);

            if (loc.getBlock().getType().equals(Material.PRISMARINE_SLAB)) {
                (event.getPlayer()).setVelocity(new Vector(0, 1.2, 0));
            }

        }
    }


}
