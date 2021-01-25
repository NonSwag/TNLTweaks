package net.nonswag.tnl.tweaks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length >= 1 && sender.hasPermission("tnl.admin")) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage("§8[§f§lTNL§8] §4" + args[0] + " §cis unknown to us");
                    return false;
                } else if (!player.isOnline()) {
                    sender.sendMessage("§8[§f§lTNL§8] §4" + player.getName() + " §cis Offline");
                    return false;
                } else {
                    if (sender.getName().equalsIgnoreCase(player.getName())) {
                        sender.sendMessage("§8[§f§lTNL§8] §aYour Ping is §6" + ((CraftPlayer) sender).getHandle().ping);
                    } else {
                        sender.sendMessage("§8[§f§lTNL§8] §6" + player.getName() + "'s §aPing is §6" + ((CraftPlayer) player).getHandle().ping);
                    }
                }
            } else {
                sender.sendMessage("§8[§f§lTNL§8] §aYour Ping is §6" + ((CraftPlayer) sender).getHandle().ping);
            }
        } else {
            sender.sendMessage("§8[§f§lTNL§8] §cThis is a Player Command");
        }
        return false;
    }
}
