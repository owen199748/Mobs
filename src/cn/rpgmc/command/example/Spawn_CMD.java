package cn.rpgmc.command.example;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import cn.rpgmc.bean.spawn.PointSpawn;
import cn.rpgmc.bean.spawn.Spawn;
import cn.rpgmc.bean.spawn.WorldSpawn;
import cn.rpgmc.command.PluginCommand;
import cn.rpgmc.run.Main;
import cn.rpgmc.utils.Send;

public class Spawn_CMD implements PluginCommand{

	@Override
	public String[] getBranch() {
		// TODO 自动生成的方法存根
		return new String[] { "mobs", "spawn" };
	}

	@Override
	public boolean run(Player p, String[] args, String auto) {
		if (args.length == 0)
			return false;

		if (args[0].equalsIgnoreCase("select")) {
			if (args.length != 3) {
				return false;
			}
			ConfigurationSection section = null;
			if (args[1].equalsIgnoreCase("Point")) {
				section = Main.getCfg().getConfigurationSection("PointSpawn")
						.getConfigurationSection(args[2]);
				if (section == null) {
					Send.sendPluginMessage(p, "该点不存在.");
					return true;
				}
				Main.setsSpawn(new PointSpawn(section));
				Send.sendPluginMessage(p, "已经选择点:" + args[2] + ".");
				return true;
			} else if (args[1].equalsIgnoreCase("World")) {
				section = Main.getCfg().getConfigurationSection("WorldSpawn")
						.getConfigurationSection(args[2]);
				if (section == null) {
					Send.sendPluginMessage(p, "该点不存在.");
					return true;
				}
				Main.setsSpawn(new WorldSpawn(section));
				Send.sendPluginMessage(p, "已经选择点:" + args[2] + ".");
				return true;
			}
		} else if (args[0].equalsIgnoreCase("new")) {

			if (args.length != 3)
				return false;
			if (args[1].equalsIgnoreCase("Point")) {
				if (Main.getO() == null) {
					Send.sendPluginMessage(p, "请先选择一个点.");
					return true;
				}

				if (PointSpawn.isPSpawn(args[2]) != -1) {
					Send.sendPluginMessage(p, "名称已存在.");
					return true;
				}
				Main.setsSpawn(new PointSpawn(args[2], Main.getCfg()
						.getConfigurationSection("PointSpawn"), Main.getO()));
				Main.getsSpawn().save();
				Send.sendPluginMessage(p, "创建成功.");
				try {
					Main.saveYml();
				} catch (IOException e) {
					Send.sendPluginMessage(p, "保存失败.");
				}
				return true;

			} else if (args[1].equalsIgnoreCase("World")) {
				if (WorldSpawn.isWSpawn(args[2]) != -1) {
					Send.sendPluginMessage(p, "名称已存在.");
					return true;
				}
				ArrayList<World> w = new ArrayList<World>();
				w.add(p.getWorld());
				Main.setsSpawn(new WorldSpawn(args[2], Main.getCfg()
						.getConfigurationSection("WorldSpawn"), w));
				Main.getsSpawn().save();
				Send.sendPluginMessage(p, "创建成功.");
				try {
					Main.saveYml();
				} catch (IOException e) {
					Send.sendPluginMessage(p, "保存失败.");
				}
				return true;
			}

		} else if (args[0].equalsIgnoreCase("list")) {
			if (args.length != 2)
				return false;

			String s = "";
			if (args[1].equalsIgnoreCase("world")) {
				s = "世界刷新点列表:";

				for (int i = 0; i < WorldSpawn.getWmobcreates().size(); i++) {
					s += WorldSpawn.getWmobcreates().get(i).getcName();
					if (i != WorldSpawn.getWmobcreates().size() - 1) {
						s += ",";
					}
				}

			} else if (args[1].equalsIgnoreCase("point")) {
				s = "独立刷新点列表:";

				for (int i = 0; i < PointSpawn.getPmobcreates().size(); i++) {
					s += PointSpawn.getPmobcreates().get(i).getcName();
					if (i != PointSpawn.getPmobcreates().size() - 1) {
						s += ",";
					}
				}

			} else
				return false;
			Send.sendPluginMessage(p, s);
			return true;

		} else if (Main.getsSpawn() == null) {
			Send.sendPluginMessage(p,
					"请先使用/Mobs spawn select [Point/World] [刷新点名] 选中某个刷新点进行修改.");
			return true;
		}

		Spawn spawn = Main.getsSpawn();

		if (args[0].equalsIgnoreCase("killall")) {

			Main.getsSpawn().killAll();
			Send.sendPluginMessage(p, "执行成功.");
			return true;

		} else if (args[0].equalsIgnoreCase("see")) {
			p.sendMessage(spawn.getSee());
			return true;
		} else {
			return false;
		}

	}

	
	
}
