package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.NMSMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SunCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ((Player) sender).getWorld().setThundering(false);
            ((Player) sender).getWorld().setStorm(false);
        } else {
            for (World world : Bukkit.getWorlds()) {
                world.setThundering(false);
                world.setStorm(false);
            }
        }
        sender.sendMessage(NMSMain.getPrefix() + "§a Set the weather to §6Sun");
        return true;
    }
}
