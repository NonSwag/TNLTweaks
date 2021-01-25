package net.nonswag.tnl.tweaks.listeners;

import net.nonswag.tnl.listener.NMSMain;
import net.nonswag.tnl.tweaks.commands.TPSCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandListener implements Listener {

    private final boolean pluginManager = Bukkit.getPluginManager().isPluginEnabled("TNLListener");

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0];
        if (command.equalsIgnoreCase("/tps")) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("tnl.tps")) {
                TPSCommand.sendTPS(event.getPlayer());
            } else {
                event.getPlayer().sendMessage(NMSMain.getPrefix() + " §cYou have no Rights §8(§4tnl.tps§8)");
            }
        }
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event) {
        String command = event.getCommand().split(" ")[0];
        if (command.equalsIgnoreCase("tps")) {
            event.setCancelled(true);
            TPSCommand.sendTPS(event.getSender());
        }
    }
}
