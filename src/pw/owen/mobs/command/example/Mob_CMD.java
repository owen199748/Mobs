package pw.owen.mobs.command.example;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import pw.owen.mobs.bean.mob.MobModel;
import pw.owen.mobs.command.PluginCommand;
import pw.owen.mobs.run.Main;
import pw.owen.mobs.utils.Send;

public class Mob_CMD implements PluginCommand {

	@Override
	public String[] getBranch() {
		// TODO 自动生成的方法存根
		return new String[] { "mobs", "mob" };
	}

	@Override
	public boolean run(Player p, String[] args, String auto) throws Exception {
		if (args.length == 0)
			return false;
		if (args[0].equalsIgnoreCase("select")) {
			if (args.length == 1) {
				Send.sendPluginMessage(p,
						"§a  /Mobs mob select  [怪物名] 设置某个怪物的配置");
				return true;
			}
			if (args.length != 2) {
				return false;
			}

			ConfigurationSection section = Main.getCfg()
					.getConfigurationSection("MobModel")
					.getConfigurationSection(args[1]);
			if (section == null) {
				Send.sendPluginMessage(p, "该怪物不存在.");
				return true;
			}
			Main.setsMobModel(new MobModel(section));
			Send.sendPluginMessage(p, "已经选择怪物:" + args[1] + ".");
			return true;

		} else if (args[0].equalsIgnoreCase("new")) {
			if (args.length != 2)
				return false;

			if (MobModel.isMobModel(args[1]) != -1) {

				Send.sendPluginMessage(p, "名称已存在.");

			} else {

					Main.setsMobModel(new MobModel(args[1], Main.getCfg()
							.getConfigurationSection("MobModel")));
					Main.saveYml();
					Send.sendPluginMessage(p, "创建成功.");

			}
			return true;

		} else if (args[0].equalsIgnoreCase("list")) {
			String s = "怪物列表:";
			for (int i = 0; i < MobModel.getMobModels().size(); i++) {
				s += MobModel.getMobModels().get(i).getsName();
				if (i != MobModel.getMobModels().size() - 1) {
					s += ",";
				}
			}
			Send.sendPluginMessage(p, s);
			return true;
		} else if (Main.getsMobModel() == null) {
			Send.sendPluginMessage(p, "请先使用/Mobs mob select [怪物名] 选中某个怪物进行修改.");
			return true;
		}
		MobModel mm = Main.getsMobModel();

		if (args[0].equalsIgnoreCase("see")) {
			p.sendMessage(mm.getSee());
			return true;
		} else if (args[0].equalsIgnoreCase("spawn")) {
			if (args.length != 1)
				return false;

			mm.spawnMob(this, p.getEyeLocation());
			Send.sendPluginMessage(p, "创建成功.");
			return true;

		} else if (args[0].equalsIgnoreCase("killall")) {
			if (args.length != 1)
				return false;
			mm.killAll();
			Send.sendPluginMessage(p, "操作成功.");
			return true;

		}

		return false;

	}

}
