package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.api.message.ChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public class DeOPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        onCommand(sender, args);
        return false;
    }

    @SuppressWarnings("deprecation")
    public static void onCommand(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (args.length >= 1) {
            OfflinePlayer arg = Bukkit.getOfflinePlayer(args[0]);
            if (arg.hasPlayedBefore() || arg.isOnline()) {
                if (arg.isOp()) {
                    arg.setOp(false);
                    sender.sendMessage(ChatComponent.getText("%prefix% §6" + arg.getName() + "§a is no longer an operator"));
                } else {
                    sender.sendMessage(ChatComponent.getText("%prefix% §cNothing could be changed"));
                }
            } else {
                sender.sendMessage(ChatComponent.getText("%prefix% §c/deop §8[§6Operator§8]"));
            }
        } else {
            sender.sendMessage(ChatComponent.getText("%prefix% §c/deop §8[§6Operator§8]"));
        }
    }
}
