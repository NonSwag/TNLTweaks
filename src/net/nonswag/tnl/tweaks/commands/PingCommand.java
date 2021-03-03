package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.TNLListener;
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
                    sender.sendMessage(TNLListener.getInstance().getPrefix() + "§4 " + args[0] + " §cis unknown to us");
                    return false;
                } else if (!player.isOnline()) {
                    sender.sendMessage(TNLListener.getInstance().getPrefix() + "§4 " + player.getName() + " §cis Offline");
                    return false;
                } else {
                    if (sender.getName().equalsIgnoreCase(player.getName())) {
                        sender.sendMessage(TNLListener.getInstance().getPrefix() + "§a Your ping is §6" + ((CraftPlayer) sender).getHandle().ping);
                    } else {
                        sender.sendMessage(TNLListener.getInstance().getPrefix() + "§6 " + player.getName() + "'s§a ping is §6" + ((CraftPlayer) player).getHandle().ping);
                    }
                }
            } else {
                sender.sendMessage(TNLListener.getInstance().getPrefix() + "§a Your ping is §6" + ((CraftPlayer) sender).getHandle().ping);
            }
        } else {
            sender.sendMessage(TNLListener.getInstance().getPrefix() + "§c This is a Player Command");
        }
        return false;
    }
}
