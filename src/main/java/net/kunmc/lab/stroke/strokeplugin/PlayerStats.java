package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerStats {
    Map<Player, String> StrokeMap = new HashMap<>();
    Map<Player, Integer> strokeCounterMap = new HashMap<>();
    Map<Player, Float> nowYawMap = new HashMap<>();
    Map<Player, Float> nowPitchMap = new HashMap<>();
    Map<Player, Float> agoYawMap = new HashMap<>();
    Map<Player, Float> agoPitchMap = new HashMap<>();

    Map<Player, Boolean> clickMap = new HashMap<>();
    Map<Player, Boolean> countMap = new HashMap<>();
    Map<Player, Boolean> actionMap = new HashMap<>();
    Map<Player, Boolean> timerMap = new HashMap<>();
    Map<Player, Integer> taskIdMap = new HashMap<>();

    public void setStroke(Player player,String addWay){
        StringBuilder wayCode = new StringBuilder();
        if(StrokeMap.get(player)!=null){
            wayCode.append(StrokeMap.get(player));
        }
        wayCode.append(addWay);
        StrokeMap.put(player,new String(wayCode));
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

    public void setClick(Player player,boolean bool){
        clickMap.put(player,bool);
    }

    public void setCount(Player player,boolean bool){
        countMap.put(player,bool);
    }

    public void setAction(Player player,boolean bool){
        actionMap.put(player,bool);
    }

    public boolean getClick(Player player){
        clickMap.putIfAbsent(player, false);
        return clickMap.get(player);
    }

    public boolean getCount(Player player){
        countMap.putIfAbsent(player, false);
        return countMap.get(player);
    }

    public boolean getAction(Player player){
        actionMap.putIfAbsent(player, false);
        return actionMap.get(player);
    }

    public Boolean getSignal(Player player){
        timerMap.putIfAbsent(player, false);
        return timerMap.get(player);
    }

    public void setSignal(Player player,Boolean bool){
        timerMap.put(player,bool);
    }

    public void setTick(Player player){
        strokeCounterMap.putIfAbsent(player, 0);
        strokeCounterMap.put(player,1+strokeCounterMap.get(player));
    }
    public int getTick(Player player){
        strokeCounterMap.putIfAbsent(player, 0);
        return strokeCounterMap.get(player);
    }
    public void resetTick(Player player){
        strokeCounterMap.put(player,0);
    }

    public void setTaskId(Player player, int id){
        taskIdMap.put(player,id);
    }

    public int getTaskId(Player player){
        taskIdMap.putIfAbsent(player, 0);
        return taskIdMap.get(player);
    }
}