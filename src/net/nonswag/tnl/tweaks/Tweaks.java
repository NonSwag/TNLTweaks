package net.nonswag.tnl.tweaks;

import net.nonswag.tnl.listener.api.command.CommandManager;
import net.nonswag.tnl.listener.utils.PluginUpdate;
import net.nonswag.tnl.tweaks.commands.*;
import net.nonswag.tnl.tweaks.completer.*;
import net.nonswag.tnl.tweaks.listeners.CommandListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Tweaks extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);

        CommandManager commandManager = new CommandManager(this);

        commandManager.registerCommand("ping", new PingCommand(), new PingCommandTabCompleter());
        commandManager.registerCommand("tps", "tnl.tps", new TPSCommand(), new TPSCommandTabCompleter());
        commandManager.registerCommand("day", "tnl.day", new DayCommand(), new DayCommandTabCompleter());
        commandManager.registerCommand("night", "tnl.night", new NightCommand(), new NightCommandTabCompleter());
        commandManager.registerCommand("sun", "tnl.sun", new SunCommand(), new SunCommandTabCompleter());
        commandManager.registerCommand("rain", "tnl.rain", new RainCommand(), new RainCommandTabCompleter());
        commandManager.registerCommand("thunder", "tnl.thunder", new ThunderCommand(), new ThunderCommandTabCompleter());
        commandManager.registerCommand("gamemode", "tnl.gamemode", new GameModeCommand(), new GameModeCommandTabCompleter());
        commandManager.registerCommand("heal", "tnl.heal", new HealCommand(), new HealCommandTabCompleter());
        commandManager.registerCommand("feed", "tnl.feed", new FeedCommand(), new FeedCommandTabCompleter());
        commandManager.registerCommand("fly", "tnl.fly", new FlyCommand(), new FlyCommandTabCompleter());

        new PluginUpdate(this).downloadUpdate();
    }
}
