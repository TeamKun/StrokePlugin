package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {
    Map<Player, String> StrokeMap = new HashMap<>();
    Map<Player, Float> nowYawMap = new HashMap<>();
    Map<Player, Float> nowPitchMap = new HashMap<>();
    Map<Player, Float> agoYawMap = new HashMap<>();
    Map<Player, Float> agoPitchMap = new HashMap<>();

    Map<Player, Boolean> clickMap = new HashMap<>();
    Map<Player, Boolean> countMap = new HashMap<>();
    Map<Player, Boolean> chantMap = new HashMap<>();
    Map<Player, Boolean> actionMap = new HashMap<>();

    public void setStroke(Player player,String addWay){
        StringBuilder wayCode = new StringBuilder();
        if(StrokeMap.get(player)!=null){
            wayCode.append(StrokeMap.get(player));
        }
        wayCode.append(addWay);
        String stroke = new String(wayCode);
        StrokeMap.put(player,stroke);
    }

    public void delStroke(Player player){
        StrokeMap.put(player,"");
    }

    public String getStroke(Player player){
        if(StrokeMap.get(player)==null){
            this.delStroke(player);
        }
        return StrokeMap.get(player);
    }

    public void setNowYaw(Player player, float yaw){
        nowYawMap.put(player,yaw);
    }

    public void setNowPitch(Player player, float pitch){
        nowPitchMap.put(player,pitch);
    }

    public float getNowYaw(Player player){
        return nowYawMap.get(player);
    }

    public float getNowPitch(Player player){
        return nowPitchMap.get(player);
    }

    public void setAgoYaw(Player player, float yaw){
        agoYawMap.put(player,yaw);
    }

    public void setAgoPitch(Player player, float pitch){
        agoPitchMap.put(player,pitch);
    }

    public float getAgoYaw(Player player){
        return agoYawMap.get(player);
    }

    public float getAgoPitch(Player player){
        return agoPitchMap.get(player);
    }

    public float getSubtractYaw(Player player){
        return this.getNowYaw(player)-this.getAgoYaw(player);
    }

    public float getSubtractPitch(Player player){
        return this.getNowPitch(player)-this.getAgoPitch(player);
    }
}
