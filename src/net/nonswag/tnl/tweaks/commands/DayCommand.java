package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.NMSMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ((Player) sender).getWorld().setTime(0);
        } else {
            for (World world : Bukkit.getWorlds()) {
                world.setTime(0);
            }
        }
        sender.sendMessage(NMSMain.getPrefix() + " §a Set the Time to §6Day");
        return true;
    }
}
