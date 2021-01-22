package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class StrokePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new StrokeEvent(), this);
        getCommand("Stroke").setExecutor(new MotionCommandExecutor(this));
        getLogger().info("モーションプラグインが有効になりました");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("モーションプラグインが無効になりました");
    }
}