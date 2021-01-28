package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.NMSMain;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setFireTicks(0);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 5);
                player.sendMessage(NMSMain.getPrefix() + "§a You got healed");
                return true;
            } else {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getName().equalsIgnoreCase(args[0])) {
                        all.setHealth(20);
                        all.setFoodLevel(20);
                        all.setFireTicks(0);
                        all.playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 15);
                        if (!all.getName().equalsIgnoreCase(sender.getName())) {
                            all.sendMessage(NMSMain.getPrefix() + "§6 " + sender.getName() + "§a healed you");
                            sender.sendMessage(NMSMain.getPrefix() + "§a " + all.getName() + "§a got healed");
                        } else {
                            sender.sendMessage(NMSMain.getPrefix() + "§a You got healed");
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
