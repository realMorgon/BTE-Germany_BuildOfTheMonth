package io.github.realMorgon.botm;

import eu.decentsoftware.holograms.api.DHAPI;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.*;

public class Hologram {

    private static final int entries = 3;

    public static void create(Location location) {
        // Create a hologram

        ArrayList<String> keys = new ArrayList<>(SaveConfig.get().getKeys(false));

        Map.Entry<Integer, String>[] relevantEntries = new Map.Entry[entries];

        for (int i = 0; i < entries; i++) {

            TreeMap<Integer, String> map = new TreeMap<>();

            for (String key : keys) {
                map.put(SaveConfig.get().getInt(key), key);
            }
            relevantEntries[i] = map.lastEntry();
            keys.remove(relevantEntries[i].getValue());
        }

        List<String> lines = new ArrayList<>();
        lines.add(ChatColor.GOLD + "Build of the Month");
        for (int i = 0; i < entries; i++) lines.add(ChatColor.GRAY + relevantEntries[i].getValue() + ": " + ChatColor.GREEN + relevantEntries[i].getKey());

        DHAPI.createHologram("BOTM", location, lines);

    }

    public static void update() {

        Location location = DHAPI.getHologram("BOTM").getLocation();
        DHAPI.removeHologram("BOTM");
        create(location);

    }


}
