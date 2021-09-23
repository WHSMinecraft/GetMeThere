package de.whsminecraft.GetMeThere;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataStore {
    private Plugin plugin;
    private Map<String, Location> locations;

    public DataStore(Plugin plugin) {
        this.plugin = plugin;
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void readFromDisk() {
        ConfigurationSection locationSection = plugin.getConfig().getConfigurationSection("");
        Set<String> locationNames = locationSection.getKeys(false);
        plugin.getLogger().info("List of locations: " + locationNames.toArray().toString());

        locations = new HashMap<>();
        for (String name : locationNames) {
            Location l = new Location();
            l.name = name;
            l.x = locationSection.getInt(name + ".x");
            l.y = locationSection.getInt(name + ".y");
            l.z = locationSection.getInt(name + ".z");
            l.yaw = (float) locationSection.getDouble(name + ".yaw");
            l.pitch = (float) locationSection.getDouble(name + ".pitch");
            String worldName = locationSection.getString(name + ".world");
            if (worldName == null) {
                worldName = "world";
            }
            l.world = Bukkit.getWorld(worldName);
            if (l.world == null) {
                plugin.getLogger().severe("No world \"" + worldName + "\" was found for location \"" + name + "\". Skipping...");
            } else {
                locations.put(name, l);
            }
        }
    }
}
