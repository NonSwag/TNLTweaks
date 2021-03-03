package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.TNLListener;
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
                    sender.sendMessage(TNLListener.getInstance().getPrefix() + "§a You can fly now");
                } else {
                    ((Player) sender).setAllowFlight(false);
                    ((Player) sender).setFlying(false);
                    sender.sendMessage(TNLListener.getInstance().getPrefix() + "§c You can't fly now");
                }
                return true;
            } else {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getName().equalsIgnoreCase(args[0])) {
                        if (!all.getAllowFlight()) {
                            all.setAllowFlight(true);
                            all.setFlying(true);
                            if (all.getName().equalsIgnoreCase(sender.getName())) {
                                sender.sendMessage(TNLListener.getInstance().getPrefix() + "§a You can fly now");
                            } else {
                                all.sendMessage(TNLListener.getInstance().getPrefix() + "§6 " + sender.getName() + "§a enabled your fly mode");
                                sender.sendMessage(TNLListener.getInstance().getPrefix() + "§a You enabled §6" + all.getName() + "'s §aFly Mode");
                            }
                        } else {
                            all.setAllowFlight(false);
                            all.setFlying(false);
                            if (all.getName().equalsIgnoreCase(sender.getName())) {
                                sender.sendMessage(TNLListener.getInstance().getPrefix() + "§c You can't fly now");
                            } else {
                                all.sendMessage(TNLListener.getInstance().getPrefix() + "§4 " + sender.getName() + "§c disabled your fly mode");
                                sender.sendMessage(TNLListener.getInstance().getPrefix() + " §c You disabled §4" + all.getName() + "'s§c fly mode");
                            }
                        }
                        return true;
                    }
                }
                sender.sendMessage(TNLListener.getInstance().getPrefix() + "§4 " + args[0] + " §cis Offline");
                return false;
            }
        } else {
            sender.sendMessage(TNLListener.getInstance().getPrefix() + " §c This is a Player Command");
            return false;
        }
    }
}
