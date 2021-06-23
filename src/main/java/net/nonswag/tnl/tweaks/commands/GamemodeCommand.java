package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.api.gamemode.Gamemode;
import net.nonswag.tnl.listener.api.message.ChatComponent;
import net.nonswag.tnl.listener.api.player.TNLPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length >= 1) {
                TNLPlayer player;
                if (args.length >= 2) {
                    player = TNLPlayer.cast(args[1]);
                    if (player == null) {
                        sender.sendMessage(ChatComponent.getText("%prefix%§4 " + args[1] + "§c is not Online"));
                        return false;
                    }
                } else player = TNLPlayer.cast((Player) sender);
                Gamemode gamemode = Gamemode.cast(args[0]);
                if (gamemode.isUnknown()) {
                    sender.sendMessage(ChatComponent.getText("%prefix%§c /gamemode §8[§6Mode§8] §8[§6Player§8]"));
                    return false;
                }
                if (player.getGamemode().equals(gamemode)) {
                    sender.sendMessage(ChatComponent.getText("%prefix%§c Nothing has changed"));
                } else {
                    player.setGamemode(gamemode);
                    player.sendMessage(ChatComponent.getText("%prefix%§a Your gamemode is now §6" + gamemode.getName()));
                    if (!player.getName().equalsIgnoreCase(sender.getName())) {
                        sender.sendMessage(ChatComponent.getText("%prefix%§6" + player.getName() + "'s§a gamemode is now §6" + gamemode.getName()));
                    }
                }
                return true;
            } else {
                sender.sendMessage(ChatComponent.getText("%prefix%§c /gamemode §8[§6Mode§8] §8[§6Player§8]"));
                return false;
            }
        } else {
            sender.sendMessage(ChatComponent.getText("%prefix%§c This is a Player Command"));
        }
        return false;
    }
}
