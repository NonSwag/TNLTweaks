package net.nonswag.tnl.tweaks.listeners;

import net.nonswag.tnl.listener.TNLListener;
import net.nonswag.tnl.tweaks.commands.TPSCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandListener implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0];
        if (command.equalsIgnoreCase("/tps")) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("tnl.tps")) {
                TPSCommand.sendTPS(event.getPlayer());
            } else {
                event.getPlayer().sendMessage(TNLListener.getInstance().getPrefix() + "§c You have no Rights §8(§4tnl.tps§8)");
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
