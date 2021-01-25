package net.nonswag.tnl.tweaks;

import net.nonswag.tnl.listener.NMSMain;
import net.nonswag.tnl.tweaks.commands.*;
import net.nonswag.tnl.tweaks.completer.*;
import net.nonswag.tnl.tweaks.listeners.CommandListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Tweaks extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);

        registerCommand("ping", new PingCommand(), new PingCommandTabCompleter());
        registerCommand("tps", "tnl.tps", new TPSCommand(), new TPSCommandTabCompleter());
        registerCommand("day", "tnl.day", new DayCommand(), new DayCommandTabCompleter());
        registerCommand("night", "tnl.night", new NightCommand(), new NightCommandTabCompleter());
        registerCommand("sun", "tnl.sun", new SunCommand(), new SunCommandTabCompleter());
        registerCommand("rain", "tnl.rain", new RainCommand(), new RainCommandTabCompleter());
        registerCommand("thunder", "tnl.thunder", new ThunderCommand(), new ThunderCommandTabCompleter());
        registerCommand("gamemode", "tnl.gamemode", new GameModeCommand(), new GameModeCommandTabCompleter());
        registerCommand("heal", "tnl.heal", new HealCommand(), new HealCommandTabCompleter());
        registerCommand("feed", "tnl.feed", new FeedCommand(), new FeedCommandTabCompleter());
        registerCommand("fly", "tnl.fly", new FlyCommand(), new FlyCommandTabCompleter());
    }

    public void registerCommand(@Nonnull String command,
                                @Nonnull CommandExecutor commandExecutor) {
        registerCommand(command, null, commandExecutor, null);
    }

    public void registerCommand(@Nonnull String command,
                                @Nonnull String permission,
                                @Nonnull CommandExecutor commandExecutor) {
        registerCommand(command, permission, commandExecutor, null);
    }

    public void registerCommand(@Nonnull String command,
                                @Nonnull CommandExecutor commandExecutor,
                                @Nullable TabCompleter tabCompleter) {
        registerCommand(command, null, commandExecutor, tabCompleter);
    }

    public void registerCommand(@Nonnull String command,
                                @Nullable String permission,
                                @Nonnull CommandExecutor commandExecutor,
                                @Nullable TabCompleter tabCompleter) {
        PluginCommand pluginCommand = this.getCommand(command);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(commandExecutor);
            pluginCommand.setAliases(pluginCommand.getAliases());
            if (permission != null) {
                pluginCommand.setPermission(permission);
                pluginCommand.setPermissionMessage(NMSMain.getPrefix() + " §cYou have no Rights §8(§4" + permission + "§8)");
            }
            if (tabCompleter != null) {
                pluginCommand.setTabCompleter(tabCompleter);
            }
        } else {
            NMSMain.stacktrace("The command '" + command + "' is not registered in your plugin.yml");
        }
    }
}
