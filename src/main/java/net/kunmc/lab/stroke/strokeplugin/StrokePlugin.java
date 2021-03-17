package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction.JumpPad;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction.SkyWalker;
import org.bukkit.plugin.java.JavaPlugin;

public final class StrokePlugin extends JavaPlugin {

    private static StrokePlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new StrokeEvent(), this);
        getServer().getPluginManager().registerEvents(new JumpPad(), this);
        getServer().getPluginManager().registerEvents(new SkyWalker(), this);
        getCommand("Stroke").setExecutor(new MotionCommandExecutor(this));
        getCommand("ReloadConfig").setExecutor(new MotionCommandExecutor(this));
        Config.loadConfig(false);
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