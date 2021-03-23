package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.Actions.JumpPad;
import net.kunmc.lab.stroke.strokeplugin.Actions.SkyWalker;
import net.kunmc.lab.stroke.strokeplugin.Actions.WeatherClear;
import org.bukkit.plugin.java.JavaPlugin;

public final class StrokePlugin extends JavaPlugin {

    private static StrokePlugin plugin;
    private static StrokePluginAPI api;

    public static StrokePluginAPI getApi() {
        return api;
    }

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new StrokeEvent(), this);
        getServer().getPluginManager().registerEvents(new JumpPad(), this);
        getServer().getPluginManager().registerEvents(new SkyWalker(), this);
        getCommand("Stroke").setExecutor(new MotionCommandExecutor(this));
        getCommand("ReloadConfig").setExecutor(new MotionCommandExecutor(this));
        Config.loadConfig(false);
        api = new StrokePluginAPI();
        api.registerAction(new SkyWalker(Config.getSkyWalkerDisplay(),Config.getSkyWalkerStroke(),Config.getSkyWalkerAnnounce()));
        api.registerAction(new JumpPad(Config.getJumpPadDisplay(),Config.getJumpPadStroke(),Config.getJumpPadAnnounce()));
        api.registerAction(new WeatherClear(Config.getWeatherClearDisplay(),Config.getWeatherClearStroke(),Config.getWeatherClearAnnounce()));
        getLogger().info("モーションプラグインが有効になりました");
    }

    @Override
    public void onDisable() {
        getLogger().info("モーションプラグインが無効になりました");
    }

    public static StrokePlugin getPlugin(){
        return plugin;
    }


}