package net.kunmc.lab.stroke.strokeplugin;

import net.kunmc.lab.stroke.strokeplugin.StrokeAction.SkyWalker;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction.WeatherClear;
import net.kunmc.lab.stroke.strokeplugin.StrokeAction.JumpPad;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class StrokeEvent implements Listener {
    public Player player;
    public ItemStack items;

    private final int[] actionTitle = {0,100,0};
    private final int[] cautionTitle = {5,20,5};

    PlayerStats stats = new PlayerStats();

    @EventHandler
    public void changeMainHand(PlayerItemHeldEvent event){
        player = event.getPlayer();
        ScheduleCheck(player);
        ActionResetJudge(player);
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event){
        String[][] strokes = {
                {"Miss","Miss"},
                {Config.getSkyWalkerStroke(),Config.getSkyWalkerDisplay()},
                {Config.getWeatherClearStroke(),Config.getWeatherClearDisplay()},
                {Config.getJumpPadStroke(),Config.getJumpPadDisplay()}
        };
        player = event.getPlayer();

        if(event.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_AIR")
                || event.getAction().toString().equalsIgnoreCase("RIGHT_CLICK_BLOCK")
        ){
            ScheduleCheck(player);
            stats.setClick(player,true);
        }
        PlayerMove(player, strokes);
    }

    public void PlayerMove(Player player, String[][] strokes){
        items = player.getInventory().getItemInMainHand();
        String item = items.getType().toString();

        if(stats.getClick(player)&&item.equalsIgnoreCase(Config.getRod())){
            if(!stats.getTrigger(player)){
                if(stats.getStroke(player).length()>Config.getStrokeTimes()-1){
                    player.setFireTicks(100);
                    stats.delStroke(player);
                    player.sendTitle("",ChatColor.DARK_RED +Config.getMagicWentOffAnnounce(),cautionTitle[0],cautionTitle[1],cautionTitle[2]);
                    player.getWorld().playEffect(player.getLocation(), Effect.END_GATEWAY_SPAWN,0);
                    player.damage(Config.getMagicWentOffDamage());
                }else{
                    getDirection(player);
                    if(StrokeDetection(stats.getStroke(player),strokes)>=1){
                        player.sendTitle(ChatColor.DARK_AQUA +stats.getStroke(player) ,strokes[StrokeDetection(stats.getStroke(player),strokes)][1],cautionTitle[0],1000,cautionTitle[2]);
                        stats.setTrigger(player,true);
                    }
                }
            }
            Timer(player,strokes);
        }
    }

    public void getDirection(Player player){
        stats.setNowPitch(player, player.getLocation().getPitch());
        stats.setNowYaw(player, player.getLocation().getYaw());
        if(!stats.getCount(player)){
            stats.setAgoYaw(player, stats.getNowYaw(player));
            stats.setAgoPitch(player, stats.getNowPitch(player));
            stats.setCount(player,true);
        }
        //I think Config.getStrokeDegree() should be set 12. if it is 15, become KUN's mouse configuration. if it is 5, nobody can control.
        if(Math.abs(stats.getSubtractPitch(player))>Config.getStrokeDegree()){
            if(Math.signum(stats.getSubtractPitch(player))==1){
                stats.setStroke(player,"↓");
            }else{
                stats.setStroke(player,"↑");
            }
            player.sendTitle("",stats.getStroke(player),actionTitle[0],actionTitle[1],actionTitle[2]);
            stats.setAgoPitch(player, stats.getNowPitch(player));
            stats.setAgoYaw(player, stats.getNowYaw(player));//この行をコメントアウトすると超ハイセンシになるが非推奨
        }
        if(Math.abs(stats.getSubtractYaw(player))>Config.getStrokeDegree()){
            if(Math.signum(stats.getSubtractYaw(player))==1){
                stats.setStroke(player,"→");
            }else{
                stats.setStroke(player,"←");
            }
            player.sendTitle("",stats.getStroke(player),actionTitle[0],actionTitle[1],actionTitle[2]);
            stats.setAgoPitch(player, stats.getNowPitch(player));//この行をコメントアウトすると超ハイセンシになるが非推奨
            stats.setAgoYaw(player, stats.getNowYaw(player));
        }
    }

    //↑↓→←
    public void StrokeAction(Player player,String stroke,String[][] strokes){
        if(stroke.equalsIgnoreCase(strokes[1][0])){
            SkyWalker skywalker = new SkyWalker();
            skywalker.skywall(player,stroke);
        }else if(stroke.equalsIgnoreCase(strokes[2][0])){
            WeatherClear weather = new WeatherClear();
            weather.weatherclear(player,stroke);
        }else if(stroke.equalsIgnoreCase(strokes[3][0])){
            JumpPad jumppad = new JumpPad();
            jumppad.DropPad(player,stroke);
        }
    }

    public Integer StrokeDetection(String stroke,String[][] strokes) {
        for (int i = 1; i < strokes.length; i++) {
            if (stroke.equalsIgnoreCase(strokes[i][0])) {
                stats.setWaitAction(player, true);
                return i;
            }
        }
        stats.setWaitAction(player, false);
        return 0;
    }

    public void ActionResetJudge(Player player){
        if(stats.getStroke(player).length()>0){
            player.sendTitle("",ChatColor.DARK_RED +stats.getStroke(player),actionTitle[0],cautionTitle[1],actionTitle[2]);
            stats.delStroke(player);
            stats.setCount(player,false);
            stats.resetTick(player);
            stats.setSignal(player,false);
            stats.setTrigger(player,false);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VEX_DEATH,1,7);
            player.getWorld().createExplosion(player.getLocation().subtract(0,-1,0),0);

        }
        stats.setWaitAction(player,false);
    }

    public void ScheduleCheck(Player player){
        if(stats.getTaskId(player)!=0){
            Bukkit.getServer().getScheduler().cancelTask(stats.getTaskId(player));
            stats.setTaskId(player, 0);
        }
    }

    public void Timer(Player player,String[][] strokes){
        BukkitRunnable task = new BukkitRunnable() {
            public void run() {
                stats.setTaskId(player, this.getTaskId());
                if(stats.getTick(player)>Config.getStrokeTimer()){
                    if(stats.getWaitAction(player)){
                        StrokeAction(player,stats.getStroke(player),strokes);
                        stats.delStroke(player);
                        stats.setCount(player, false);
                        stats.setTrigger(player,false);
                        ScheduleCheck(player);
                        return;
                    }
                    ActionResetJudge(player);
                    stats.delStroke(player);
                    stats.setCount(player, false);
                    stats.setTrigger(player,false);
                    ScheduleCheck(player);
                    this.cancel();
                }
                stats.setTick(player);
                Bukkit.getLogger().info(this.getTaskId()+" : "+stats.getTick(player));
            }
        };
        stats.setClick(player,false);//クリック判定の解除

        if(!stats.getSignal(player)){
            task.runTaskTimer(StrokePlugin.getPlugin(), 0L,1L);
        }
        stats.resetTick(player);
    }
}
