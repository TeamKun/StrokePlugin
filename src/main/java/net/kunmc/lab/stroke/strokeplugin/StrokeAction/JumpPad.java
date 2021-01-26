package net.kunmc.lab.stroke.strokeplugin.StrokeAction;

import net.kunmc.lab.stroke.strokeplugin.StrokePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.bukkit.scheduler.BukkitRunnable;

public class JumpPad implements Listener{

    public void DropPad(Player player){
        Entity ball = player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SNOWBALL);
        ball.setVelocity(player.getLocation().getDirection());
        player.sendTitle("","JumpPad dropped!",5,10,5);
    }

    @EventHandler
    public void BallBreak(ProjectileHitEvent event){
        Block block;

        if(event.getEntity().getType().toString().equalsIgnoreCase("SNOWBALL")){
            block = event.getEntity().getLocation().getBlock();
            block.setType(Material.PRISMARINE_SLAB);

            BukkitRunnable task = new BukkitRunnable() {
                public void run() {
                    block.setType(Material.AIR);
                    cancel();
                    return;
                }
            };
            task.runTaskTimer(StrokePlugin.getPlugin(), 100L,0L);
        }
    }

    @EventHandler
    public void jumppad(PlayerMoveEvent event){
        if((event.getPlayer()).isOnGround())
        {
            Location loc=event.getTo();
            Location p = loc.clone();
            p.add(0,-0.1,0);

            if(loc.getBlock().getType().equals(Material.PRISMARINE_SLAB))
            {
                (event.getPlayer()).setVelocity(new Vector(0,1.2,0));
            }

        }
    }

}
