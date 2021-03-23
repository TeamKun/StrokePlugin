package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.Actions.JumpPad;

import java.util.HashMap;
import java.util.Map;

public class StrokePluginAPI {
    private Map<String,StrokeAction> strokes = new HashMap<>();

    //Strokeが競合した場合はfalseを返却する
    public boolean registerAction(StrokeAction action) {
        if (strokes.containsKey(action.getStroke())) return false;
        strokes.put(action.getStroke(), action);
        return true;
    }

    public StrokeAction getAction(String stroke) {
        return strokes.get(stroke);
    }

    public boolean isExist(String stroke) {
        return strokes.containsKey(stroke);
    }
}
