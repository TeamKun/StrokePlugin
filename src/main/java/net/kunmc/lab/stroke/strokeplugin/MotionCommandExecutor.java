package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class MotionCommandExecutor implements CommandExecutor{

    private final StrokePlugin plugin;
    int[] cautionTitle = {5,30,5};

    public MotionCommandExecutor(StrokePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        Player player = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("Stroke@Title"))
        {
            if (!(sender instanceof Player)) {
                sender.sendMessage("プレーヤーのみが実行できます");
                return false;
            }
            if(!sender.hasPermission("stroke.permission")){
                sender.sendMessage("権限がありません ;>_<; 管理者に問い合わせてください。");
                return false;
            }
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendTitle("Stroke Plugin","制作 CrewL2020",cautionTitle[0],cautionTitle[1],cautionTitle[2]);
            }
        }
        else if(cmd.getName().equalsIgnoreCase("Stroke@ReloadConfig"))
        {
            if(!sender.hasPermission("stroke.permission")){
                sender.sendMessage("権限がありません ;>_<; 管理者に問い合わせてください。");
                return false;
            }
            Config.loadConfig(true);
            sender.sendMessage("Config.yml was reloaded");
            return true;
        }
        else if(cmd.getName().equalsIgnoreCase("Stroke@help"))
        {
            if(args.length == 0){
                sender.sendMessage(Config.getHelp());
            }else if(args.length == 1){
                if(!sender.hasPermission("stroke.permission")){
                    sender.sendMessage("権限がありません ;>_<; 管理者に問い合わせてください。");
                    return false;
                }
                List<Entity> targets = Bukkit.selectEntities(sender,args[0]);
                if(targets.isEmpty()){
                    sender.sendMessage("対象が見つかりません");
                    return false;
                }
                for(Entity target:targets){
                    Player p = (Player)target;
                    p.sendMessage(Config.getHelp());
                }
            }else{
                sender.sendMessage("引数はプレイヤーだけです。 : /Stroke@description [player]");
            }
            return true;
        }
        else if(cmd.getName().equalsIgnoreCase("Stroke@list"))
        {
            if(args.length == 0){
                StrokePluginAPI.getCommandList(player);
            }else if(args.length == 1){
                if(!sender.hasPermission("stroke.permission")){
                    sender.sendMessage("権限がありません ;>_<; 管理者に問い合わせてください。");
                    return false;
                }
                List<Entity> targets = Bukkit.selectEntities(sender,args[0]);
                if(targets.isEmpty()){
                    sender.sendMessage("対象が見つかりません");
                    return false;
                }
                for(Entity target:targets){
                    Player p = (Player)target;
                    StrokePluginAPI.getCommandList(p);
                }
            }else{
                sender.sendMessage("引数はプレイヤーだけです。 : /Stroke@list [player]");
            }
            return true;
        }
        else if(cmd.getName().equalsIgnoreCase("Stroke@description"))
        {
            if(args.length == 0){
                sender.sendMessage(Config.getExplain());
            }else if(args.length == 1){
                if(!sender.hasPermission("stroke.permission")){
                    sender.sendMessage("権限がありません ;>_<; 管理者に問い合わせてください。");
                    return false;
                }
                List<Entity> targets = Bukkit.selectEntities(sender,args[0]);
                if(targets.isEmpty()){
                    sender.sendMessage("対象が見つかりません");
                    return false;
                }
                for(Entity target:targets){
                    Player p = (Player)target;
                    p.sendMessage(Config.getExplain());
                }
            }else{
                sender.sendMessage("引数はプレイヤーだけです。 : /Stroke@description [player]");
            }
            return true;
        }
        else if(cmd.getName().equalsIgnoreCase("Stroke@descriptionFormal"))

        {
            if(args.length == 0){
                sender.sendMessage(Config.getExplainFormal());
            }
            else if(args.length == 1)
            {
                if(!sender.hasPermission("stroke.permission")){
                    sender.sendMessage("権限がありません ;>_<; 管理者に問い合わせてください。");
                    return false;
                }
                List<Entity> targets = Bukkit.selectEntities(sender,args[0]);
                if(targets.isEmpty()){
                    sender.sendMessage("対象が見つかりません");
                    return false;
                }
                for(Entity target:targets){
                    Player p = (Player)target;
                    p.sendMessage(Config.getExplainFormal());
                }
            }
            else
            {
                sender.sendMessage("引数はプレイヤーだけです。 : /Stroke@descriptionFormal [player]");
            }
            return true;
        }

        return false;
    }
}
