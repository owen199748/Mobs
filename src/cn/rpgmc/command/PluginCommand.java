package cn.rpgmc.command;

import org.bukkit.entity.Player;

public interface PluginCommand {

	public String[] getBranch();

	public boolean run(Player p, String[] args, String auto);

}
