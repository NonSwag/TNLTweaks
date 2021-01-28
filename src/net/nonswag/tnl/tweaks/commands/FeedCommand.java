package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.NMSMain;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 5);
                player.sendMessage(NMSMain.getPrefix() + "§a Your hunger has been satisfied");
                return true;
            } else {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getName().equalsIgnoreCase(args[0])) {
                        all.setFoodLevel(20);
                        all.setSaturation(20);
                        all.playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 15);
                        if (!all.getName().equalsIgnoreCase(sender.getName())) {
                            all.sendMessage(NMSMain.getPrefix() + "§6 " + sender.getName() + "§a satisfied your hunger");
                            sender.sendMessage(NMSMain.getPrefix() + "§a" + all.getName() + "§a hunger has been satisfied");
                        } else {
                            sender.sendMessage(NMSMain.getPrefix() + "§a Your hunger has been satisfied");
                        }
                        return true;
                    }
                }
                sender.sendMessage(NMSMain.getPrefix() + "§4 " + args[0] + " §cis Offline");
                return false;
            }
        } else {
            sender.sendMessage(NMSMain.getPrefix() + "§c This is a Player Command");
            return false;
        }
    }
}
