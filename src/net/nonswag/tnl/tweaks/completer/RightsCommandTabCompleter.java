package net.nonswag.tnl.tweaks.completer;

import net.nonswag.tnl.listener.TNLListener;
import net.nonswag.tnl.listener.api.player.TNLPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RightsCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            suggestions.add("add");
            suggestions.add("remove");
            suggestions.add("list");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    suggestions.add(all.getName());
                }
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("remove")) {
                TNLPlayer arg = TNLListener.getInstance().getPlayer(args[1]);
                if (arg != null) {
                    suggestions.addAll(arg.getPermissionManager().getPermissions());
                }
            }
        }
        suggestions.removeIf(tabCompleter -> !tabCompleter.toLowerCase().startsWith(args[args.length - 1].toLowerCase()));
        return suggestions;
    }
}
