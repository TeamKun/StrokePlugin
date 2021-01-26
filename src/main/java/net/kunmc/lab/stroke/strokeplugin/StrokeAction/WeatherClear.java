package net.kunmc.lab.stroke.strokeplugin.StrokeAction;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WeatherClear{

    public void weatherclear(Player player, String stroke){
        player.getWorld().setStorm(false);
        player.sendTitle("WeatherClear happen!",ChatColor.AQUA +stroke,0,10,0);
    }

}
