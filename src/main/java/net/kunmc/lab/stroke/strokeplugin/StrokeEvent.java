package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction.WeatherClear;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class StrokeEvent implements Listener {
    public Player player;
    public StringBuilder wayCode = new StringBuilder();
    public String stroke;
    public float playerPitch, playerYaw, basePitch, baseYaw;
    public ItemStack items;
    public final int sensi = 12;
    //I think sensi should be set 12. if it is 15, become KUN's mouse configuration. if it is 5, nobody can control.

    boolean count = false;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){

        player = event.getPlayer();

        items = player.getInventory().getItemInMainHand();
        String item = items.getType().toString();

        if(item.equalsIgnoreCase("END_ROD")){
            playerPitch = player.getLocation().getPitch();
            playerYaw = player.getLocation().getYaw();
            if(!count){
                basePitch = playerPitch;
                baseYaw = playerYaw;
                count = true;
            }

            if(Math.abs(playerPitch-basePitch)>sensi){
                if(Math.signum(playerPitch-basePitch)==1){
                    wayCode.append("D");
                    player.sendTitle("","↓",10,70,20);
                }else{
                    wayCode.append("U");
                    player.sendTitle("","↑",10,70,20);
                }
                basePitch = playerPitch;
                baseYaw = playerYaw;//この行をコメントアウトすると超ハイセンシになるが非推奨
            }
            if(Math.abs(playerYaw-baseYaw)>sensi){
                if(Math.signum(playerYaw-baseYaw)==1){
                    wayCode.append("R");
                    player.sendTitle("","→",10,70,20);
                }else{
                    wayCode.append("L");
                    player.sendTitle("","←",10,70,20);
                }
                basePitch = playerPitch;//この行をコメントアウトすると超ハイセンシになるが非推奨
                baseYaw = playerYaw;
            }

            if(wayCode.length()>=5){
                String s = new String(wayCode);
                stroke = s.substring(0,5);
                StrokeAction(player,stroke);
                wayCode.delete(0,10);
            }
        }else{
            wayCode.delete(0,10);
        }
    }

    public void StrokeAction(Player player,String stroke){
        switch(stroke){
            case "LURRD":
                WeatherClear weather = new WeatherClear();
                weather.weatherclear(player);
                break;
            default:
                player.sendTitle("","ミス",10,70,20);
                break;
        }
    }

}
