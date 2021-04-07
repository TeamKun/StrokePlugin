package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.Actions.Attack;
import net.kunmc.lab.stroke.strokeplugin.Actions.JumpPad;
import net.kunmc.lab.stroke.strokeplugin.Actions.SkyWalker;
import net.kunmc.lab.stroke.strokeplugin.Actions.WeatherClear;
import org.bukkit.plugin.java.JavaPlugin;

public final class StrokePlugin extends JavaPlugin {

    private static StrokePlugin plugin;
    private static StrokePluginAPI api;

    public static StrokePlugin getPlugin() {
        return plugin;
    }

    public static StrokePluginAPI getApi() {
        return api;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Config.loadConfig(false);
        api = new StrokePluginAPI();
        //api.registerAction(new Attack(Config.getAttack("display"), Config.getAttack("stroke"), Config.getAttack("announce"), Config.getAttack("explain")));
        api.registerAction(new SkyWalker(Config.getSkyWalker("display"), Config.getSkyWalker("stroke"), Config.getSkyWalker("announce"), Config.getSkyWalker("explain")));
        api.registerAction(new JumpPad(Config.getJumpPad("display"), Config.getJumpPad("stroke"), Config.getJumpPad("announce"), Config.getJumpPad("explain")));
        api.registerAction(new WeatherClear(Config.getWeatherClear("display"), Config.getWeatherClear("stroke"), Config.getWeatherClear("announce"), Config.getWeatherClear("explain")));

        getServer().getPluginManager().registerEvents(new StrokeEvent(), this);
        getServer().getPluginManager().registerEvents(new JumpPad(), this);
        getServer().getPluginManager().registerEvents(new SkyWalker(), this);
        getCommand("Stroke@Title").setExecutor(new MotionCommandExecutor(this));
        getCommand("Stroke@ReloadConfig").setExecutor(new MotionCommandExecutor(this));
        getCommand("Stroke@help").setExecutor(new MotionCommandExecutor(this));
        getCommand("Stroke@list").setExecutor(new MotionCommandExecutor(this));
        getCommand("Stroke@description").setExecutor(new MotionCommandExecutor(this));
        getCommand("Stroke@descriptionFormal").setExecutor(new MotionCommandExecutor(this));
        getLogger().info("モーションプラグインが有効になりました");
    }

    @Override
    public void onDisable() {
        getLogger().info("モーションプラグインが無効になりました");
    }
}
