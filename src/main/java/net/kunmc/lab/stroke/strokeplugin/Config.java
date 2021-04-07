package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.configuration.file.FileConfiguration;

/*
   Thank you for helping BOSATSU!!
 */

public class Config {
    private static String SystemHelp;
    private static String SystemExplain;
    private static String SystemExplainFormal;

    private static String SkyWalkerDisplay;
    private static String SkyWalkerStroke;
    private static String SkyWalkerAnnounce;
    private static String SkyWalkerExplain;
    private static int SkyWalkerFloor;

    private static String WeatherClearDisplay;
    private static String WeatherClearStroke;
    private static String WeatherClearAnnounce;
    private static String WeatherClearExplain;

    private static String JumpPadDisplay;
    private static String JumpPadStroke;
    private static String JumpPadAnnounce;
    private static String JumpPadExplain;

    private static String AttackDisplay;
    private static String AttackStroke;
    private static String AttackAnnounce;
    private static String AttackExplain;

    private static String rod;
    private static String magic_went_off_announce;
    private static int magic_went_off_damage;
    private static int stroke_degree;
    private static int stroke_timer;
    private static int stroke_times;
    private static String no_select;

    public static void loadConfig(boolean isReload) {

        StrokePlugin plugin = StrokePlugin.getPlugin();

        plugin.saveDefaultConfig();

        // リロード処理
        if (isReload) {
            plugin.reloadConfig();
        }

        //コンフィグファイルを取得
        FileConfiguration config = plugin.getConfig();

        // 各値を代入
        SystemHelp = config.getString("System.Help");
        SystemExplain = config.getString("System.Explain");
        SystemExplainFormal = config.getString("System.Explain-Formal");

        SkyWalkerDisplay = config.getString("actions.SkyWalker.display");
        SkyWalkerStroke = config.getString("actions.SkyWalker.stroke");
        SkyWalkerAnnounce = config.getString("actions.SkyWalker.announce");
        SkyWalkerExplain = config.getString("actions.SkyWalker.explain");
        SkyWalkerFloor = config.getInt("actions.SkyWalker.floor_size");

        WeatherClearDisplay = config.getString("actions.WeatherClear.display");
        WeatherClearStroke = config.getString("actions.WeatherClear.stroke");
        WeatherClearAnnounce = config.getString("actions.WeatherClear.announce");
        WeatherClearExplain = config.getString("actions.WeatherClear.explain");

        JumpPadDisplay = config.getString("actions.JumpPad.display");
        JumpPadStroke = config.getString("actions.JumpPad.stroke");
        JumpPadAnnounce = config.getString("actions.JumpPad.announce");
        JumpPadExplain = config.getString("actions.JumpPad.explain");

        AttackDisplay = config.getString("actions.Attack.display");
        AttackStroke = config.getString("actions.Attack.stroke");
        AttackAnnounce = config.getString("actions.Attack.announce");
        AttackExplain = config.getString("actions.Attack.explain");

        rod = config.getString("rod_item");
        magic_went_off_announce = config.getString("magic_went_off.announce");
        magic_went_off_damage = config.getInt("magic_went_off.damage");
        stroke_degree = config.getInt("stroke.degree");
        stroke_timer = config.getInt("stroke.timer");
        stroke_times = config.getInt("stroke.maxStroke");
        no_select = config.getString("no_select");
    }

    public static Integer getSkyWalkerFloor() {
        return Config.SkyWalkerFloor;
    }

    public static String getSkyWalker(String mode) {
        if(mode.equalsIgnoreCase("display")){
            return Config.SkyWalkerDisplay;
        }else if(mode.equalsIgnoreCase("stroke")){
            return Config.SkyWalkerStroke;
        }else if(mode.equalsIgnoreCase("announce")){
            return Config.SkyWalkerAnnounce;
        }else if(mode.equalsIgnoreCase("explain")){
            return Config.SkyWalkerExplain;
        }else{
            return no_select;
        }
    }

    public static String getWeatherClear(String mode) {
        if(mode.equalsIgnoreCase("display")){
            return Config.WeatherClearDisplay;
        }else if(mode.equalsIgnoreCase("stroke")){
            return Config.WeatherClearStroke;
        }else if(mode.equalsIgnoreCase("announce")){
            return Config.WeatherClearAnnounce;
        }else if(mode.equalsIgnoreCase("explain")){
            return Config.WeatherClearExplain;
        }else{
            return no_select;
        }
    }

    public static String getJumpPad(String mode) {
        if(mode.equalsIgnoreCase("display")){
            return Config.JumpPadDisplay;
        }else if(mode.equalsIgnoreCase("stroke")){
            return Config.JumpPadStroke;
        }else if(mode.equalsIgnoreCase("announce")){
            return Config.JumpPadAnnounce;
        }else if(mode.equalsIgnoreCase("explain")){
            return Config.JumpPadExplain;
        }else{
            return no_select;
        }
    }

    public static String getAttack(String mode) {
        if(mode.equalsIgnoreCase("display")){
            return Config.AttackDisplay;
        }else if(mode.equalsIgnoreCase("stroke")){
            return Config.AttackStroke;
        }else if(mode.equalsIgnoreCase("announce")){
            return Config.AttackAnnounce;
        }else if(mode.equalsIgnoreCase("explain")){
            return Config.AttackExplain;
        }else{
            return no_select;
        }
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

    public static String getHelp() {
        return Config.SystemHelp;
    }

    public static String getExplain() {
        return SystemExplain;
    }

    public static String getExplainFormal() {
        return SystemExplainFormal;
    }
}
