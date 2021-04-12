package net.nonswag.tnl.tweaks.completer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeOPCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (sender.hasPermission("tnl.rights") && args.length <= 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (all.isOp()) {
                    suggestions.add(all.getName());
                }
            }
        }
        return suggestions;
    }
}
