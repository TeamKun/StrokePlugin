package net.kunmc.lab.stroke.strokeplugin.Actions;

import net.kunmc.lab.stroke.strokeplugin.Config;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction;
import net.kunmc.lab.stroke.strokeplugin.StrokePlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyWalker implements Listener, StrokeAction {
    private final int r = Config.getSkyWalkerFloor();//半径
    int[] cautionTitle = {5, 20, 5};
    private String name;
    private String stroke;
    private String announce;
    private String description;

    public SkyWalker() {

    }

    public SkyWalker(String name, String stroke, String announce, String description) {
        this.name = name;
        this.stroke = stroke;
        this.announce = announce;
        this.description = description;
    }

    @Override
    public void run(Player player) {
        BukkitRunnable task = new BukkitRunnable() {
            int count = 0;

            public void run() {
                Location startLoc = player.getLocation().subtract(r, 1, r);

                for (int i = startLoc.getBlockX(); i < startLoc.getBlockX() + r * 2; i++) {
                    for (int k = startLoc.getBlockZ(); k < startLoc.getBlockZ() + r * 2; k++) {
                        Location loc = new Location(startLoc.getWorld(), i, startLoc.getBlockY(), k);
                        Block b = loc.getBlock();

                        if (b.getType() == Material.AIR) {
                            b.setType(Material.CYAN_STAINED_GLASS);

                            BukkitRunnable task3 = new BukkitRunnable() {
                                public void run() {
                                    b.setType(Material.AIR);
                                    cancel();
                                }
                            };
                            task3.runTaskTimer(StrokePlugin.getPlugin(), 60L, 0L);
                        }
                    }
                }
                if (count > 200) {
                    cancel();
                }
                count++;
            }
        };
        task.runTaskTimer(StrokePlugin.getPlugin(), 0L, 1L);
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