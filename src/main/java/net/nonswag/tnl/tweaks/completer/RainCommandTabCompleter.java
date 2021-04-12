package net.nonswag.tnl.tweaks.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RainCommandTabCompleter implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
