package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.TNLListener;
import net.nonswag.tnl.listener.api.message.ChatComponent;
import net.nonswag.tnl.listener.api.player.TNLPlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            TNLPlayer  player = TNLPlayer.cast((Player) sender);
            if (args.length == 0) {
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.getBukkitPlayer().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 5);
                player.sendMessage("%prefix%§a Your hunger has been satisfied");
                return true;
            } else {
                for (TNLPlayer  all : TNLListener.getInstance().getOnlinePlayers()) {
                    if (all.getName().equalsIgnoreCase(args[0])) {
                        all.setFoodLevel(20);
                        all.setSaturation(20);
                        all.getBukkitPlayer().playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 15);
                        if (!all.getName().equalsIgnoreCase(sender.getName())) {
                            all.sendMessage("%prefix%§6 " + sender.getName() + "§a satisfied your hunger");
                            player.sendMessage("%prefix%§a" + all.getName() + "§a hunger has been satisfied");
                        } else {
                            player.sendMessage("%prefix%§a Your hunger has been satisfied");
                        }
                        return true;
                    }
                }
                player.sendMessage("%prefix%§4 " + args[0] + " §cis Offline");
                return false;
            }
        } else {
            sender.sendMessage(ChatComponent.getText("%prefix%§c This is a Player Command"));
            return false;
        }
    }
}
