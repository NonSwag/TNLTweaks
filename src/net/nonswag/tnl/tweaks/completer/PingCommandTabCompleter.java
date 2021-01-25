package net.nonswag.tnl.tweaks.completer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PingCommandTabCompleter implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabCompletions = new ArrayList<>();
        if (args.length == 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                tabCompletions.add(all.getName());
            }
        }
        tabCompletions.removeIf(tabCompleter -> !tabCompleter.toLowerCase().startsWith(args[args.length - 1].toLowerCase()));
        return tabCompletions;
    }
}
