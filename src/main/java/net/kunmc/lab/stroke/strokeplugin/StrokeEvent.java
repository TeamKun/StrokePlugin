package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction.SkyWalker;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction.WeatherClear;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction.JumpPad;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
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
    boolean action = false;

    private final int[] actionTitle = {0,100,0};
    private final int[] cautionTitle = {5,20,5};

    @EventHandler
    public void changeMainHand(PlayerItemHeldEvent event){
        player = event.getPlayer();

        if(wayCode.length()>0){
            stroke = new String(wayCode);
            int len = stroke.length();
            player.sendTitle("",ChatColor.DARK_RED +stroke,actionTitle[0],cautionTitle[1],actionTitle[2]);
            wayCode.delete(0,len);
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
            if(wayCode.length()>9){
                player.setFireTicks(100);
                wayCode.delete(0,20);
                click = false;
                chant = false;
                player.sendTitle("",ChatColor.DARK_RED +"魔力が暴走した。",cautionTitle[0],cautionTitle[1],cautionTitle[2]);
                player.getWorld().createExplosion(player.getLocation(),0);
                player.damage(4);
            }else{
                playerPitch = player.getLocation().getPitch();
                playerYaw = player.getLocation().getYaw();
                if(!count){
                    basePitch = playerPitch;
                    baseYaw = playerYaw;
                    count = true;
                }

                if(Math.abs(playerPitch-basePitch)>sensi){
                    if(Math.signum(playerPitch-basePitch)==1){
                        wayCode.append("↓");
                    }else{
                        wayCode.append("↑");
                    }
                    player.sendTitle("",new String(wayCode),actionTitle[0],actionTitle[1],actionTitle[2]);
                    basePitch = playerPitch;
                    baseYaw = playerYaw;//この行をコメントアウトすると超ハイセンシになるが非推奨
                }
                if(Math.abs(playerYaw-baseYaw)>sensi){
                    if(Math.signum(playerYaw-baseYaw)==1){
                        wayCode.append("→");
                    }else{
                        wayCode.append("←");
                    }
                    player.sendTitle("",new String(wayCode),actionTitle[0],actionTitle[1],actionTitle[2]);
                    basePitch = playerPitch;//この行をコメントアウトすると超ハイセンシになるが非推奨
                    baseYaw = playerYaw;
                }
                chant = true;//詠唱中を保持

                stroke = new String(wayCode);
                int len = stroke.length();
                StrokeAction(player,stroke);
                if(action){
                    wayCode.delete(0,len);
                    chant = false;
                    action = false;
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
