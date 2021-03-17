package net.kunmc.lab.stroke.strokeplugin.StrokeAction;

import net.kunmc.lab.stroke.strokeplugin.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WeatherClear{

    public void weatherclear(Player player, String stroke){
        player.getWorld().setStorm(false);
        player.sendTitle(Config.getWeatherClearAnnounce(),ChatColor.AQUA +stroke,0,20,0);
    }

}
