package net.nonswag.tnl.tweaks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                if (!((Player) sender).getAllowFlight()) {
                    ((Player) sender).setAllowFlight(true);
                    ((Player) sender).setFlying(true);
                    sender.sendMessage("§8[§f§lTNL§8] §aYou can Fly now");
                } else {
                    ((Player) sender).setAllowFlight(false);
                    ((Player) sender).setFlying(false);
                    sender.sendMessage("§8[§f§lTNL§8] §cYou can't Fly now");
                }
                return true;
            } else {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getName().equalsIgnoreCase(args[0])) {
                        if (!all.getAllowFlight()) {
                            all.setAllowFlight(true);
                            all.setFlying(true);
                            if (all.getName().equalsIgnoreCase(sender.getName())) {
                                sender.sendMessage("§8[§f§lTNL§8] §aYou can Fly now");
                            } else {
                                all.sendMessage("§8[§f§lTNL§8] §6" + sender.getName() + " §aenabled Your Fly Mode");
                                sender.sendMessage("§8[§f§lTNL§8] §aYou enabled §6" + all.getName() + "'s §aFly Mode");
                            }
                        } else {
                            all.setAllowFlight(false);
                            all.setFlying(false);
                            if (all.getName().equalsIgnoreCase(sender.getName())) {
                                sender.sendMessage("§8[§f§lTNL§8] §cYou can't Fly now");
                            } else {
                                all.sendMessage("§8[§f§lTNL§8] §4" + sender.getName() + " §cdisabled Your Fly Mode");
                                sender.sendMessage("§8[§f§lTNL§8] §cYou disabled §4" + all.getName() + "'s §cFly Mode");
                            }
                        }
                        return true;
                    }
                }
                sender.sendMessage("§8[§f§lTNL§8] §4" + args[0] + " §cis Offline");
                return false;
            }
        } else {
            sender.sendMessage("§8[§f§lTNL§8] §cThis is a Player Command");
            return false;
        }
    }
}
