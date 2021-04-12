package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.api.message.ChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.sendMessage(ChatComponent.getText("%prefix%§a You can fly now"));
                } else {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.sendMessage(ChatComponent.getText("%prefix%§c You can't fly now"));
                }
                return true;
            } else {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.getName().equalsIgnoreCase(args[0])) {
                        if (!all.getAllowFlight()) {
                            all.setAllowFlight(true);
                            all.setFlying(true);
                            if (all.getName().equalsIgnoreCase(sender.getName())) {
                                player.sendMessage(ChatComponent.getText("%prefix%§a You can fly now"));
                            } else {
                                all.sendMessage(ChatComponent.getText("%prefix%§6 " + sender.getName() + "§a enabled your fly mode"));
                                player.sendMessage(ChatComponent.getText("%prefix%§a You enabled §6" + all.getName() + "'s §aFly Mode"));
                            }
                        } else {
                            all.setAllowFlight(false);
                            all.setFlying(false);
                            if (all.getName().equalsIgnoreCase(sender.getName())) {
                                player.sendMessage(ChatComponent.getText("%prefix%§c You can't fly now"));
                            } else {
                                all.sendMessage(ChatComponent.getText("%prefix%§4 " + sender.getName() + "§c disabled your fly mode"));
                                player.sendMessage(ChatComponent.getText("%prefix%  §c You disabled §4" + all.getName() + "'s§c fly mode"));
                            }
                        }
                        return true;
                    }
                }
                player.sendMessage(ChatComponent.getText("%prefix%§4 " + args[0] + " §cis Offline"));
                return false;
            }
        } else {
            sender.sendMessage(ChatComponent.getText("%prefix%  §c This is a Player Command"));
            return false;
        }
    }
}
