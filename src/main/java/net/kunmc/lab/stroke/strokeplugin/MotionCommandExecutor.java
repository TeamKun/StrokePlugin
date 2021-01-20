package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

public class MotionCommandExecutor implements CommandExecutor{

    private final StrokePlugin plugin;

    public MotionCommandExecutor(StrokePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        String user = sender.getName();
        sender.sendMessage(user);
        if (cmd.getName().equalsIgnoreCase("item"))
        {
            //
            if (!(sender instanceof Player)) {
                sender.sendMessage("プレーヤーのみが実行できます");
                return false;
            }
            if(args.length!=0){
                sender.sendMessage("余計なパラメータを入れないでください");
                return false;
            }
            Player player = (Player)sender;

            ItemStack item = player.getInventory().getItemInMainHand();
            player.sendMessage(item.toString());


        }
        else if (cmd.getName().equalsIgnoreCase("OK"))
        {
            //set conditions that can be taken.
            if (!(sender instanceof Player)) {
                sender.sendMessage("プレーヤーのみが実行できます");
                return false;
            }
            if(args.length!=0){
                sender.sendMessage("余計なパラメータを入れないでください");
                return false;
            }
            Player player = (Player)sender;
            player.getWorld().setTime(300L);
            player.getWorld().setStorm(false);

            double health = player.getHealthScale();
            sender.sendMessage(String.valueOf(health));
        }

        return false;

    }
}
