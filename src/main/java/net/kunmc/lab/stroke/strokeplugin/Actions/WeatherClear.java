package net.kunmc.lab.stroke.strokeplugin.Actions;

import net.kunmc.lab.stroke.strokeplugin.Config;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WeatherClear implements StrokeAction {
    private String name;
    private String stroke;
    private String announce;


    public WeatherClear() {

    }

    public WeatherClear(String name, String stroke, String announce) {
        this.name = name;
        this.stroke = stroke;
        this.announce = announce;
    }

    @Override
    public void run(Player player, String stroke) {
        player.getWorld().setStorm(false);
        player.sendTitle(Config.getWeatherClearAnnounce(),ChatColor.AQUA +stroke,0,20,0);
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
}
