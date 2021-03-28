package net.nonswag.tnl.tweaks.listeners;

import net.nonswag.tnl.listener.TNLListener;
import net.nonswag.tnl.listener.api.message.Message;
import net.nonswag.tnl.listener.api.message.Placeholder;
import net.nonswag.tnl.tweaks.commands.DeOPCommand;
import net.nonswag.tnl.tweaks.commands.OPCommand;
import net.nonswag.tnl.tweaks.commands.TPSCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.Arrays;

public class CommandListener implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0];
        if (command.equalsIgnoreCase("/tps")) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("tnl.tps")) {
                TPSCommand.sendTPS(event.getPlayer());
            } else {
                TNLListener.getInstance().getPlayer(event.getPlayer()).sendMessage(Message.NO_PERMISSION_EN, new Placeholder("permission", "tnl.tps"));
            }
        } else if (command.equalsIgnoreCase("/op")) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("tnl.rights")) {
                String[] s = event.getMessage().split(" ");
                OPCommand.onCommand(event.getPlayer(), Arrays.asList(s).subList(1, s.length).toArray(new String[]{}));
            } else {
                TNLListener.getInstance().getPlayer(event.getPlayer()).sendMessage(Message.NO_PERMISSION_EN, new Placeholder("permission", "tnl.rights"));
            }
        } else if (command.equalsIgnoreCase("/deop")) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("tnl.rights")) {
                String[] s = event.getMessage().split(" ");
                DeOPCommand.onCommand(event.getPlayer(), Arrays.asList(s).subList(1, s.length).toArray(new String[]{}));
            } else {
                TNLListener.getInstance().getPlayer(event.getPlayer()).sendMessage(Message.NO_PERMISSION_EN, new Placeholder("permission", "tnl.rights"));
            }
        }
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event) {
        String command = event.getCommand().split(" ")[0];
        if (command.equalsIgnoreCase("tps")) {
            event.setCancelled(true);
            TPSCommand.sendTPS(event.getSender());
        } else if (command.equalsIgnoreCase("op")) {
            event.setCancelled(true);
            String[] s = event.getCommand().split(" ");
            OPCommand.onCommand(event.getSender(), Arrays.asList(s).subList(1, s.length).toArray(new String[]{}));
        } else if (command.equalsIgnoreCase("deop")) {
            event.setCancelled(true);
            String[] s = event.getCommand().split(" ");
            DeOPCommand.onCommand(event.getSender(), Arrays.asList(s).subList(1, s.length).toArray(new String[]{}));
        }
    }
}
