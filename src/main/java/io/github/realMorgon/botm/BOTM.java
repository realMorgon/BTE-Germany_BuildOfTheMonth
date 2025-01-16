package io.github.realMorgon.botm;

import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class BOTM extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        SaveConfig.setup();

        getCommand("botm").setExecutor(new BOTMCommand());

        Location location = SaveConfig.get().getLocation(":location");
        SaveConfig.get().set(":location", null);
        SaveConfig.save();

        if (location != null) {
            Hologram.create(location);
        }else {
            Bukkit.getLogger().warning("[BOTM] No location found in config!");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (DHAPI.getHologram("BOTM") == null) return;
        SaveConfig.get().set(":location", DHAPI.getHologram("BOTM").getLocation());
        SaveConfig.save();
    }
}
