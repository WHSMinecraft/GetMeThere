package de.whsminecraft.GetMeThere;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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

        locations = new HashMap<>();
        for (String name : locationNames) {
            String worldName = locationSection.getString(name + ".world");
            if (worldName == null) {
                worldName = "world";
            }
            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                plugin.getLogger().severe("No world \"" + worldName + "\" was found for location \"" + name + "\". Skipping...");
                continue;
            }

            Location l = new Location(
                    world,
                    locationSection.getDouble(name + ".x"),
                    locationSection.getDouble(name + ".y"),
                    locationSection.getDouble(name + ".z"),
                    (float) locationSection.getDouble(name + ".yaw"),
                    (float) locationSection.getDouble(name + ".pitch")
            );
            locations.put(name, l);
        }
    }
}
