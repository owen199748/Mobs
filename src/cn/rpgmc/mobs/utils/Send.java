package cn.rpgmc.mobs.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import cn.rpgmc.mobs.run.Main;

public class Send {
	public static ChatColor COLOR = ChatColor.AQUA;

public static void sendConsole(String s){
		Bukkit.getConsoleSender().sendMessage(
				"¡ìe[" + Main.getMain().getName() + "] ¡ìb"
						+ s.replaceAll("&", "¡ì"));
}
public static void sendPluginMessage(Player p,String s){
		p.sendMessage("¡ìc¡ìl[Mobs]¡ì3" + s.replaceAll("&", "¡ì"));
}

}
