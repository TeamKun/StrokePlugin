package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.configuration.file.FileConfiguration;

/*
   Thank you for helping BOSATSU!!
 */

public class Config {
    private static String SkyWalkerDisplay;
    private static String SkyWalkerStroke;
    private static String SkyWalkerAnnounce;
    private static int SkyWalkerFloor;

    private static String WeatherClearDisplay;
    private static String WeatherClearStroke;
    private static String WeatherClearAnnounce;

    private static String JumpPadDisplay;
    private static String JumpPadStroke;
    private static String JumpPadAnnounce;

    private static String rod;
    private static String magic_went_off_announce;
    private static int magic_went_off_damage;
    private static int stroke_degree;
    private static int stroke_timer;
    private static int stroke_times;

    public static void loadConfig(boolean isReload) {

        StrokePlugin plugin = StrokePlugin.getPlugin();

        plugin.saveDefaultConfig();

        // リロード処理
        if (isReload) {
            plugin.reloadConfig();
        }

        //　コンフィグファイルを取得
        FileConfiguration config = plugin.getConfig();

        // 各値を代入
        SkyWalkerDisplay = config.getString("actions.SkyWalker.display");
        SkyWalkerStroke = config.getString("actions.SkyWalker.stroke");
        SkyWalkerAnnounce = config.getString("actions.SkyWalker.announce");
        SkyWalkerFloor = config.getInt("actions.SkyWalker.floor_size");

        WeatherClearDisplay = config.getString("actions.WeatherClear.display");
        WeatherClearStroke = config.getString("actions.WeatherClear.stroke");
        WeatherClearAnnounce = config.getString("actions.WeatherClear.announce");

        JumpPadDisplay = config.getString("actions.JumpPad.display");
        JumpPadStroke = config.getString("actions.JumpPad.stroke");
        JumpPadAnnounce = config.getString("actions.JumpPad.announce");

        rod = config.getString("rod_item");
        magic_went_off_announce = config.getString("magic_went_off.announce");
        magic_went_off_damage = config.getInt("magic_went_off.damage");
        stroke_degree = config.getInt("stroke.degree");
        stroke_timer = config.getInt("stroke.timer");
        stroke_times = config.getInt("stroke.maxStroke");

    }

    public static String getSkyWalkerDisplay() {
        return Config.SkyWalkerDisplay;
    }

    public static String getSkyWalkerStroke() {
        return Config.SkyWalkerStroke;
    }

    public static String getSkyWalkerAnnounce() {
        return Config.SkyWalkerAnnounce;
    }

    public static Integer getSkyWalkerFloor() {
        return Config.SkyWalkerFloor;
    }

    public static String getWeatherClearDisplay() {
        return Config.WeatherClearDisplay;
    }

    public static String getWeatherClearStroke() {
        return Config.WeatherClearStroke;
    }

    public static String getWeatherClearAnnounce() {
        return Config.WeatherClearAnnounce;
    }

    public static String getJumpPadDisplay() {
        return Config.JumpPadDisplay;
    }

    public static String getJumpPadStroke() {
        return Config.JumpPadStroke;
    }

    public static String getJumpPadAnnounce() {
        return Config.JumpPadAnnounce;
    }

    public static String getRod() {
        return Config.rod;
    }

    public static String getMagicWentOffAnnounce() {
        return Config.magic_went_off_announce;
    }

    public static Integer getMagicWentOffDamage() {
        return Config.magic_went_off_damage;
    }

    public static Integer getStrokeDegree() {
        return Config.stroke_degree;
    }

    public static Integer getStrokeTimer() {
        return Config.stroke_timer;
    }

    public static Integer getStrokeTimes() {
        return Config.stroke_times;
    }
}
