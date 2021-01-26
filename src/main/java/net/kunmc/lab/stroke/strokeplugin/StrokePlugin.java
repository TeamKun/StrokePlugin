package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction.JumpPad;
import org.bukkit.plugin.java.JavaPlugin;

public final class StrokePlugin extends JavaPlugin {

    private static StrokePlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new StrokeEvent(), this);
        getServer().getPluginManager().registerEvents(new JumpPad(), this);
        getCommand("Stroke").setExecutor(new MotionCommandExecutor(this));
        getLogger().info("モーションプラグインが有効になりました");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("モーションプラグインが無効になりました");
    }

    public static StrokePlugin getPlugin(){
        return plugin;
    }
}