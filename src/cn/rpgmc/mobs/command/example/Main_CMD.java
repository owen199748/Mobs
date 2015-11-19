package cn.rpgmc.mobs.command.example;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import cn.rpgmc.mobs.bean.mob.Mob;
import cn.rpgmc.mobs.bean.mob.MobModel;
import cn.rpgmc.mobs.bean.skill.Skill;
import cn.rpgmc.mobs.bean.spawn.PointSpawn;
import cn.rpgmc.mobs.bean.spawn.WorldSpawn;
import cn.rpgmc.mobs.command.PluginCommand;
import cn.rpgmc.mobs.run.Main;
import cn.rpgmc.mobs.utils.Send;

import com.google.common.io.Files;

public class Main_CMD implements PluginCommand {

	@Override
	public String[] getBranch() {
		// TODO 自动生成的方法存根
		return new String[] { "mobs" };
	}
	
	
	@Override
	public boolean run(Player p, String[] args, String auto) throws Exception {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("set")) {

				if (args.length == 1) {
					if (p.getItemInHand() == null
							| p.getItemInHand().getType().getId() == Material.AIR
									.getId()) {
						Send.sendPluginMessage(p, "您将一个物品拿在手上之后再执行该命令.");
						return true;

					}

					Main.setClickItem(p.getItemInHand().getTypeId());
					Send.sendPluginMessage(p, "您已经将"
							+ p.getItemInHand().getType().name() + "作为点选择器.");
					return true;
				} else {
					return false;
				}

			} else if (args[0].equalsIgnoreCase("setban")) {

				if (args.length != 3) {
					return false;
				} else {
					if (args[1].equalsIgnoreCase("Animal")) {
						if (args[2].equalsIgnoreCase("true")) {
							for (int i = 0; i < Main
									.getAnimalSpawnBannedWorld().size(); i++) {
								if (Main.getAnimalSpawnBannedWorld()
										.get(i)
										.equalsIgnoreCase(
												p.getWorld().getName())) {
									Main.getAnimalSpawnBannedWorld().remove(
											p.getWorld().getName());
								}

							}

							Main.getAnimalSpawnBannedWorld().add(
									p.getWorld().getName());
						} else if (args[2].equalsIgnoreCase("false")) {
							Main.getAnimalSpawnBannedWorld().remove(
									p.getWorld().getName());
						} else {
							return false;
						}

					} else if (args[1].equalsIgnoreCase("Monster")) {
						if (args[2].equalsIgnoreCase("true")) {
							for (int i = 0; i < Main
									.getMonsterSpawnBannedWorld().size(); i++) {
								if (Main.getMonsterSpawnBannedWorld()
										.get(i)
										.equalsIgnoreCase(
												p.getWorld().getName())) {
									Main.getMonsterSpawnBannedWorld().remove(
											p.getWorld().getName());
								}

							}

							Main.getMonsterSpawnBannedWorld().add(
									p.getWorld().getName());

						} else if (args[2].equalsIgnoreCase("false")) {
							Main.getMonsterSpawnBannedWorld().remove(
									p.getWorld().getName());
						} else {
							return false;
						}

					} else {
						return false;
					}


						Main.saveYml();
						Send.sendPluginMessage(p, "设置成功.");
						return true;


				}

			} else if (args[0].equalsIgnoreCase("listban")) {
				String s1 = "禁止产生动物:";
				String s2 = "禁止产生怪物:";
				ArrayList<String> al = Main.getAnimalSpawnBannedWorld();
				ArrayList<String> ml = Main.getMonsterSpawnBannedWorld();
				for (int i = 0; i < al.size(); i++) {
					s1 += al.get(i);
					if (i != al.size() - 1) {
						s1 += ",";
					}
				}

				for (int i = 0; i < ml.size(); i++) {
					s2 += ml.get(i);
					if (i != ml.size() - 1) {
						s2 += ",";
					}
				}
				Send.sendPluginMessage(p, s1);
				Send.sendPluginMessage(p, s2);
				return true;
			} else if (args[0].equalsIgnoreCase("listPotionEffectType")) {
				String str = "支持的药水类型:";
				for (int i = 0; i < PotionEffectType.values().length; i++) {
					if (PotionEffectType.values()[i] != null) {
						if (i != 0)
						str += ",";

					str += PotionEffectType.values()[i].getName();
				}
				}
				Send.sendPluginMessage(p, str);

				return true;
			} else if (args[0].equalsIgnoreCase("listEntityType")) {
				String str = "支持的怪物类型:";
				for (int i = 0; i < EntityType.values().length; i++) {
					if (i != 0)
						str += ",";

					str += EntityType.values()[i].name();
				}
				Send.sendPluginMessage(p, str);

				return true;
			} else if (args[0].equalsIgnoreCase("reload")) {
				if (args.length == 1) {

						Main.getCfg().load(Main.getF());
					// Main.getCfg().set("Version", Main.getV());
						Main.loadYml();
						Mob.killAll();
						Send.sendPluginMessage(p, "配置重载成功.");

				} else {
					return false;
				}
				return true;
			} else if (args[0].equalsIgnoreCase("update")) {
				if (args.length == 1) {

					Main.getCfg().load(Main.getF());
					Main.getCfg().set("Version", Main.getV());
					updateYml();
					Main.loadYml();
					Mob.killAll();
					Send.sendPluginMessage(p, "配置升级成功.");

				} else {
					return false;
				}
				return true;
			} else if (args[0].equalsIgnoreCase("killall")) {
				if (args.length == 1) {
					Mob.killAll();
					Send.sendPluginMessage(p, "操作成功.");

			} else {
					return false;
				}

				return true;
			} else {

				return false;
			}
		}

		return false;

	}

	private void updateYml() throws Exception {
		File f = new File(Main.getF().getAbsolutePath() + ".bak");
		Files.copy(Main.getF(), f);
		YamlConfiguration cfg1 = new YamlConfiguration();

		cfg1.load(f);
		FileConfiguration cfg = Main.getCfg();

		ConfigurationSection m = Main.getCfg().getConfigurationSection(
				"MobModel");
		ConfigurationSection ws = Main.getCfg().getConfigurationSection(
				"WorldSpawn");
		ConfigurationSection ps = Main.getCfg().getConfigurationSection(
				"PointSpawn");
		ConfigurationSection skill = Main.getCfg().getConfigurationSection(
				"Skill");
		
		Object[] key = m.getKeys(false).toArray();
		for (int i = 0; i < key.length; i++) {
			m.set((String) key[i], null);
			new MobModel((String) key[i], m).save();
		}

		key = ws.getKeys(false).toArray();
		for (int i = 0; i < key.length; i++) {
			ws.set((String) key[i], null);
			new WorldSpawn((String) key[i], ws, null)
					.save();
		}

		key = ps.getKeys(false).toArray();
		for (int i = 0; i < key.length; i++) {
			ps.set((String) key[i], null);
			new PointSpawn((String) key[i], ps, null)
					.save();
		}

		key = skill.getKeys(false).toArray();
		for (int i = 0; i < key.length; i++) {
			String type = skill.getConfigurationSection(
(String) key[i])
					.getString(
					"type");
			skill.set((String) key[i], null);
			Skill.newSkill(type, (String) key[i])
					.save();
		}

		cfg.setDefaults(cfg1);
		moveTo(cfg1, cfg);
		f.delete();
		cfg.save(Main.getF());

	}


	private void moveTo(ConfigurationSection a, ConfigurationSection b) {
		Object[] key = a.getKeys(false).toArray();
		for (int i = 0; i < key.length; i++) {
			Object v = a.get((String) key[i]);
			if (v instanceof ConfigurationSection) {
				if (b.getConfigurationSection((String) key[i]) == null)
					b.createSection((String) key[i]);
				moveTo((ConfigurationSection) v,
						b.getConfigurationSection((String) key[i]));

			} else
				b.set((String) key[i], v);

		}

	}
	
	


	
}
