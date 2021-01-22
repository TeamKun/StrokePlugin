package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction.WeatherClear;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
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

    boolean click = false;
    boolean count = false;
    boolean chant = false;

    int fadeIn = 5;
    int stay = 10;
    int fadeOut = 5;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){

        player = event.getPlayer();

        items = player.getInventory().getItemInMainHand();
        String item = items.getType().toString();

        if(item.equalsIgnoreCase("END_ROD")){
            if(wayCode.length()>9){
                player.setFireTicks(100);
                wayCode.delete(0,20);
                click = false;
                chant = false;
                player.sendTitle("","魔力が暴走した。",fadeIn,stay,fadeOut);
                player.getWorld().createExplosion(player.getLocation(),0);
                player.damage(4);
            }else if(click||chant){
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
                        player.sendTitle("","↓",fadeIn,stay,fadeOut);
                    }else{
                        wayCode.append("U");
                        player.sendTitle("","↑",fadeIn,stay,fadeOut);
                    }
                    basePitch = playerPitch;
                    baseYaw = playerYaw;//この行をコメントアウトすると超ハイセンシになるが非推奨
                }
                if(Math.abs(playerYaw-baseYaw)>sensi){
                    if(Math.signum(playerYaw-baseYaw)==1){
                        wayCode.append("R");
                        player.sendTitle("","→",fadeIn,stay,fadeOut);
                    }else{
                        wayCode.append("L");
                        player.sendTitle("","←",fadeIn,stay,fadeOut);
                    }
                    basePitch = playerPitch;//この行をコメントアウトすると超ハイセンシになるが非推奨
                    baseYaw = playerYaw;
                }

                chant = true;//詠唱中を保持
            }
        }else{
            wayCode.delete(0,20);
            click = false;
            chant = false;
            count = false;
        }
        click = false;//クリック判定の解除
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event){
        items = player.getInventory().getItemInMainHand();
        String item = items.getType().toString();

        if(event.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_AIR")){
            click = true;
            if(chant&&item.equalsIgnoreCase("END_ROD")){
                stroke = new String(wayCode);
                int len = stroke.length();
                StrokeAction(player,stroke);
                wayCode.delete(0,len);
                click = false;
                chant = false;
            }
        }
    }

    public void StrokeAction(Player player,String stroke){
        switch(stroke){
            case "LURRD":
                WeatherClear weather = new WeatherClear();
                weather.weatherclear(player);
                break;
            case "UD":

                break;
            default:
                player.sendTitle("","ミス",fadeIn,stay,fadeOut);
                break;
        }
    }

}
