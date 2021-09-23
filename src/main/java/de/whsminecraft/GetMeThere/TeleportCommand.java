package de.whsminecraft.GetMeThere;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class TeleportCommand extends BukkitCommand {
    private Plugin plugin;
    private DataStore dataStore;

    public TeleportCommand(String name, Plugin plugin, DataStore dataStore) {
        super(name);
        this.description = "Teleport to this location";
        this.usageMessage = "/" + name;
        this.setAliases(new ArrayList<String>());

        this.plugin = plugin;
        this.dataStore = dataStore;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (args.length != 0)
            return false;

        if (!(sender instanceof Player)) {
            String err = "/" + s + " is only available to players";
            plugin.getLogger().info(err);
            sender.sendMessage(ChatColor.RED + err);
            return true;
        }



        Player player = (Player) sender;
        String locationName = s;
        if (s.contains(":")) {
            String[] a = s.split(":");
            locationName = a[a.length-1];
        }
        Location l = dataStore.getLocations().get(locationName);
        plugin.getLogger().info(
                "Teleporting " + player.getDisplayName() +
                        " to " + locationName +
                        " (x=" + l.getX() +
                        " y=" + l.getY() +
                        " z=" + l.getZ() +
                        " world=" + l.getWorld().getName() +
                        ")");
        player.teleport(l);
        return true;
    }
}
