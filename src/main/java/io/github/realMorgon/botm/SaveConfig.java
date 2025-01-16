package io.github.realMorgon.botm;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Objects;

public class SaveConfig {

    private static File file;
    private static FileConfiguration config;

    public static void setup(){

        file = new File((Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("BOTM"))).getDataFolder(), "saves.yml");
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (Exception ignored){}
        }

        config = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration get(){
        return config;
    }

    public static void save(){
        try{
            config.save(file);
        }catch (Exception IOException){
            System.out.println("Couldn't save Config");
        }
    }

}
