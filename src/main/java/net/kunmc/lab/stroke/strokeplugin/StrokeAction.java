package net.kunmc.lab.stroke.strokeplugin;

import org.bukkit.entity.Player;

public interface StrokeAction {
    void run(Player player, String stroke);
    String getName(); //configのdisplayに対応するゲッター
    String getStroke();
    String getAnnounce();
}