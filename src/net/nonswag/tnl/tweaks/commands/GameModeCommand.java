package net.nonswag.tnl.tweaks.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length >= 1) {
                Player player;
                if (args.length >= 2) {
                    player = Bukkit.getPlayer(args[1]);
                    if (player == null) {
                        sender.sendMessage("§8[§f§lTNL§8] §4" + args[1] + " §cis unknown to us");
                        return false;
                    } else if (!player.isOnline()) {
                        sender.sendMessage("§8[§f§lTNL§8] §4" + player.getName() + " §cis Offline");
                        return false;
                    }
                } else {
                    player = (Player) sender;
                }
                GameMode gameMode;
                if (args[0].equalsIgnoreCase("s") ||
                        args[0].equalsIgnoreCase("survival") ||
                        args[0].equalsIgnoreCase("0")) {
                    gameMode = GameMode.SURVIVAL;
                } else if (args[0].equalsIgnoreCase("c") ||
                        args[0].equalsIgnoreCase("creative") ||
                        args[0].equalsIgnoreCase("1")) {
                    gameMode = GameMode.CREATIVE;
                } else if (args[0].equalsIgnoreCase("a") ||
                        args[0].equalsIgnoreCase("adventure") ||
                        args[0].equalsIgnoreCase("2")) {
                    gameMode = GameMode.ADVENTURE;
                } else if (args[0].equalsIgnoreCase("sp") ||
                        args[0].equalsIgnoreCase("spectator") ||
                        args[0].equalsIgnoreCase("3")) {
                    gameMode = GameMode.SPECTATOR;
                } else {
                    sender.sendMessage("§8[§f§lTNL§8] §c/gamemode §8[§6Mode§8] §8[§6Player§8]");
                    return false;
                }
                if (player.getGameMode().equals(gameMode)) {
                    sender.sendMessage("§8[§f§lTNL§8] §cNothing has changed");
                } else {
                    player.setGameMode(gameMode);
                    player.sendMessage("§8[§f§lTNL§8] §aYour GameMode is now §6" +
                            player.getGameMode().name().toLowerCase());
                    if (!player.getName().equalsIgnoreCase(sender.getName())) {
                        sender.sendMessage("§8[§f§lTNL§8] §6" + player.getName() + "'s §aGameMode is now §6" +
                                player.getGameMode().name().toLowerCase());
                    }
                }
                return true;
            } else {
                sender.sendMessage("§8[§f§lTNL§8] §c/gamemode §8[§6Mode§8] §8[§6Player§8]");
                return false;
            }
        } else {
            sender.sendMessage("§8[§f§lTNL§8] §cThis is a Player Command");
        }
        return false;
    }
}
