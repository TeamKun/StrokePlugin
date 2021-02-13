package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction.SkyWalker;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction.WeatherClear;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction.JumpPad;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class StrokeEvent implements Listener {
    public Player player;
    public ItemStack items;
    public final int sensi = 12;
    //I think sensi should be set 12. if it is 15, become KUN's mouse configuration. if it is 5, nobody can control.

    boolean click = false;
    boolean count = false;
    boolean chant = false;
    boolean action = false;

    private final int[] actionTitle = {0,100,0};
    private final int[] cautionTitle = {5,20,5};

    PlayerStats stats = new PlayerStats();

    @EventHandler
    public void changeMainHand(PlayerItemHeldEvent event){
        player = event.getPlayer();

        if(stats.getStroke(player).length()>0){
            player.sendTitle("",ChatColor.DARK_RED +stats.getStroke(player),actionTitle[0],cautionTitle[1],actionTitle[2]);
            stats.delStroke(player);
            chant = false;
            count = false;
        }
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event){
        player = event.getPlayer();

        if(event.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_AIR")
                || event.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_BLOCK")
        ){
            click = true;
        }
        PlayerMove(player);
    }

    public void PlayerMove(Player player){
        items = player.getInventory().getItemInMainHand();
        String item = items.getType().toString();

        if(click&&item.equalsIgnoreCase("BLAZE_ROD")){
            if(stats.getStroke(player).length()>9){
                player.setFireTicks(100);
                stats.delStroke(player);
                click = false;
                chant = false;
                player.sendTitle("",ChatColor.DARK_RED +"魔力が暴走した。",cautionTitle[0],cautionTitle[1],cautionTitle[2]);
                player.getWorld().createExplosion(player.getLocation(),0);
                player.damage(4);
            }else{
                stats.setNowPitch(player, player.getLocation().getPitch());
                stats.setNowYaw(player, player.getLocation().getYaw());
                if(!count){
                    stats.setAgoYaw(player, stats.getNowYaw(player));
                    stats.setAgoPitch(player, stats.getNowPitch(player));
                    count = true;
                }

                if(Math.abs(stats.getSubtractPitch(player))>sensi){
                    if(Math.signum(stats.getSubtractPitch(player))==1){
                        stats.setStroke(player,"↓");
                    }else{
                        stats.setStroke(player,"↑");
                    }
                    player.sendTitle("",stats.getStroke(player),actionTitle[0],actionTitle[1],actionTitle[2]);
                    stats.setAgoPitch(player, stats.getNowPitch(player));
                    stats.setAgoYaw(player, stats.getNowYaw(player));//この行をコメントアウトすると超ハイセンシになるが非推奨
                }
                if(Math.abs(stats.getSubtractYaw(player))>sensi){
                    if(Math.signum(stats.getSubtractYaw(player))==1){
                        stats.setStroke(player,"→");
                    }else{
                        stats.setStroke(player,"←");
                    }
                    player.sendTitle("",stats.getStroke(player),actionTitle[0],actionTitle[1],actionTitle[2]);
                    stats.setAgoPitch(player, stats.getNowPitch(player));//この行をコメントアウトすると超ハイセンシになるが非推奨
                    stats.setAgoYaw(player, stats.getNowYaw(player));
                }
                chant = true;//詠唱中を保持
                
                StrokeAction(player,stats.getStroke(player));
                if(action){
                    stats.delStroke(player);
                    chant = false;
                    action = false;
                    count = false;
                }
            }
        }
        click = false;//クリック判定の解除
    }

    //↑↓→←
    public void StrokeAction(Player player,String stroke){
        switch(stroke){
            case "→←→←":
                SkyWalker skywalker = new SkyWalker();
                skywalker.skywall(player,stroke);
                action = true;
                break;
            case "←↑→":
                WeatherClear weather = new WeatherClear();
                weather.weatherclear(player,stroke);
                action = true;
                break;
            case "↓↑":
                JumpPad jumppad = new JumpPad();
                jumppad.DropPad(player,stroke);
                action = true;
                break;
        }

    }
}
