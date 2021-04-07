package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StrokePluginAPI {
    private static final Map<String, StrokeAction> strokes = new HashMap<>();

    //Strokeが競合した場合はfalseを返却する
    public boolean registerAction(StrokeAction action) {
        if (strokes.containsKey(action.getStroke())) return false;
        strokes.put(action.getStroke(), action);
        return true;
    }

    public StrokeAction getAction(String stroke) {
        return strokes.get(stroke);
    }

    public boolean isExistKey(String stroke) {
        return strokes.containsKey(stroke);
    }

    public static void getCommandList(Player player){
        for(String key : strokes.keySet()){
            player.sendMessage(strokes.get(key).getDescription());
        }
    }
}
