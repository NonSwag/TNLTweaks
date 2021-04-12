package net.nonswag.tnl.tweaks.commands;

import net.nonswag.tnl.listener.api.message.ChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TPSCommand implements CommandExecutor {

    public static void sendTPS(CommandSender sender) {
        StringBuilder s = new StringBuilder("%prefix%§7 TPS from last 1m§8, §75m§8, §715m§8: §6");
        double[] tps = Bukkit.getTPS();
        for (int i = 0; i < tps.length; i++) {
            s.append(format(tps[i]));
            if (i + 1 != tps.length) {
                s.append("§8, §a");
            }
        }
        double free = (Runtime.getRuntime().freeMemory() / 1024d) / 1024d;
        double total = (Runtime.getRuntime().totalMemory() / 1024d) / 1024d;
        double used = (total - free);
        sender.sendMessage(ChatComponent.getText(s.toString()));
        sender.sendMessage(ChatComponent.getText("%prefix%§7 Memory free§8, §7used§8, §7max§8: §a" + ((int) free) + "mb§8, §a" + ((int) used) + "mb§8, §a" + ((int) total) + "mb"));
        sender.sendMessage(ChatComponent.getText("%prefix%§7 Memory display§8: " + format(((int) total), ((int) used))));
        sender.sendMessage(ChatComponent.getText("%prefix%§7 Available processors§8: §a" + Runtime.getRuntime().availableProcessors()));
    }

    public static String format(int maxRam, int usedRam) {
        float percent = (usedRam * (100f / maxRam));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            if ((percent / 100) * 20 <= i + 1) {
                s.append("§7|");
            } else {
                s.append("§4|");
            }
        }
        return "§6" + usedRam + "§8/§6" + maxRam + "mb §8» §6" + (((int) (usedRam * (100f / maxRam)))) + "§8/§6100% §8[" + s.toString() + "§8]";
    }

    private static String format(double tps) {
        double rounded = (double) Math.round(tps * 100.0D) / 100.0D;
        return (rounded >= 18.0D ? "§a" : (rounded >= 16.0D ? "§e" : "§c")) + (rounded >= 20.0D ? "*" : "") + Math.min(rounded, 20.0D);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sendTPS(sender);
        return true;
    }
}
