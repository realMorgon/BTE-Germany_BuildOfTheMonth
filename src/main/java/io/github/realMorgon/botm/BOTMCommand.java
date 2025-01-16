package io.github.realMorgon.botm;

import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BOTMCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, @NotNull Command command, @NotNull String s, String @NotNull [] strings) {

        if (commandSender.hasPermission("bteg.lobby.botm")){

            if (strings.length != 1) {
                commandSender.sendMessage(ChatColor.RED + "[BTE] Bitte benutzte '/botm <SPIELERNAME|:create|:move|:remove>'");
                return false;
            }

            if (strings[0].equalsIgnoreCase(":move")) {
                if (commandSender instanceof Player) {
                    DHAPI.moveHologram("BOTM", ((Player) commandSender).getLocation());
                    commandSender.sendMessage(ChatColor.GREEN + "[BTE] Hologramm wurde erfolgreich verschoben!");
                    return true;
                }else{
                    commandSender.sendMessage(ChatColor.RED + "[BTE] Du musst ein Spieler sein um diesen Befehl auszuführen!");
                    return false;
                }

            } else if (strings[0].equalsIgnoreCase(":create")) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(ChatColor.RED + "[BTE] Du musst ein Spieler sein um diesen Befehl auszuführen!");
                    return false;
                }else if (DHAPI.getHologram("BOTM") != null){
                    commandSender.sendMessage(ChatColor.RED + "[BTE] Es existiert bereits ein Hologramm!");
                    return false;
                }else {
                    Hologram.create(((Player) commandSender).getLocation());
                    commandSender.sendMessage(ChatColor.GREEN + "[BTE] Hologramm wurde erfolgreich erstellt!");
                    return true;
                }

            } else if (strings[0].equalsIgnoreCase(":remove")) {
                if (DHAPI.getHologram("BOTM") == null) {
                    commandSender.sendMessage(ChatColor.RED + "[BTE] Es existiert kein Hologramm!");
                    return false;
                }else {
                    DHAPI.removeHologram("BOTM");
                    commandSender.sendMessage(ChatColor.GREEN + "[BTE] Hologramm wurde erfolgreich entfernt!");
                    return true;
                }

            } else {
                try {
                    if (SaveConfig.get().get(strings[0]) == null) {
                        SaveConfig.get().set(strings[0], 1);
                        commandSender.sendMessage(ChatColor.GREEN + "[BTE] Spieler " + strings[0] + " hat nun 1 BOTM Punkt!");
                    } else {
                        SaveConfig.get().set(strings[0], SaveConfig.get().getInt(strings[0]) + 1);
                        commandSender.sendMessage(ChatColor.GREEN + "[BTE] Spieler " + strings[0] + " hat nun " + SaveConfig.get().getInt(strings[0]) + " BOTM Punkte!");
                    }
                    SaveConfig.save();
                    Hologram.update();
                    return true;
                }catch (Exception e){
                    commandSender.sendMessage(ChatColor.RED + "[BTE] Der Spielername/Das Argument ist ungültig!");
                    return false;
                }
            }

        }else {
            commandSender.sendMessage(ChatColor.RED + "[BTE] Du darfst diesen Befehl nicht ausführen!");
            return false;
        }
    }
}
