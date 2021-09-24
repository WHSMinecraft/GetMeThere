package de.whsminecraft.GetMeThere;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Map;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        DataStore ds = new DataStore(this);

        ds.readFromDisk();
        getLogger().info("Following locations are set up: " + ds.getLocations().toString());;

        for (Map.Entry<String, Location> e: ds.getLocations().entrySet()) {
            TeleportCommand cmd = new TeleportCommand(e.getKey(), this, ds);
            getCommandMap().register(getName(), cmd);
        }
        getLogger().info("Plugin was successfully enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin was successfully disabled.");
    }

    private CommandMap getCommandMap() {
        CommandMap commandMap = null;

        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                f.setAccessible(true);

                commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            }
        } catch (Exception e) {
            getLogger().severe(e.getMessage());
        }

        return commandMap;
    }
}
