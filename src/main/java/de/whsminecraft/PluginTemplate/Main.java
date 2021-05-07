package de.whsminecraft.PluginTemplate;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin was successfully enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin was successfully disabled.");
    }
}
