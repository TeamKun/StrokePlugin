package net.kunmc.lab.stroke.strokeplugin.StrokeAction;

import org.bukkit.entity.Player;

public class WeatherClear{

    public void weatherclear(Player player){
        player.getWorld().setStorm(false);
    }

}
