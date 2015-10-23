package cn.rpgmc.run;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import cn.rpgmc.bean.mob.DropItemStack;
import cn.rpgmc.bean.mob.Eqpt;
import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.mob.MobModel;
import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.bean.spawn.PointSpawn;
import cn.rpgmc.bean.spawn.Spawn;
import cn.rpgmc.bean.spawn.WorldSpawn;
import cn.rpgmc.bean.utils.Damage;
import cn.rpgmc.bean.utils.EXP;
import cn.rpgmc.bean.utils.HP;

public class Cmd {

	static boolean mobSpawn(Player p, String[] args) throws Exception {

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("spawn")) {
				if (args.length == 1) {
					spawnHelp(p);
					if (Main.getsSpawn() == null)
						return true;
					else if (Main.getsSpawn().getCreateType()
							.equalsIgnoreCase(Spawn.POINTMOBCREATE))
						pointSpawnHelp(p);
					else if (Main.getsSpawn().getCreateType()
							.equalsIgnoreCase(Spawn.WORLDMOBCREATE))
						worldSpawnHelp(p);

					return true;
				}
				return spawn(p, args);
			} else if (args[0].equalsIgnoreCase("mob")) {
				if (args.length == 1) {
					mobHelp(p);
					return true;
				}
				return mob(p, args);
			} else if (args[0].equalsIgnoreCase("set")) {

				if (args.length == 1) {
					if (p.getItemInHand() == null
							| p.getItemInHand().getType().getId() == Material.AIR
									.getId()) {
						p.sendMessage("§c[Mobs]§f您将一个物品拿在手上之后再执行该命令.");
						return true;

					}

					Main.setClickItem(p.getItemInHand().getTypeId());
					p.sendMessage("§c[Mobs]§f您已经将"
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

					try {
						Main.saveYml();
						p.sendMessage("§c[Mobs]§f设置成功.");
						return true;
					} catch (IOException e) {
						p.sendMessage("§c[Mobs]§f配置保存失败.");
						return true;
					}

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
				p.sendMessage(s1);
				p.sendMessage(s2);
				return true;
			} else if (args[0].equalsIgnoreCase("listPotionEffectType")) {
				String str = "支持的药水类型:";
				for (int i = 0; i < PotionEffectType.values().length; i++) {
					if (i != 0)
						str += ",";

					str += PotionEffectType.values()[i].getName();
				}
				p.sendMessage(str);

				return true;
			} else if (args[0].equalsIgnoreCase("listEntityType")) {
				String str = "支持的怪物类型:";
				for (int i = 0; i < EntityType.values().length; i++) {
					if (i != 0)
						str += ",";

					str += EntityType.values()[i].name();
					;
				}
				p.sendMessage(str);

				return true;
			} else if (args[0].equalsIgnoreCase("reload")) {
				if (args.length == 1) {
					try {
						Main.getCfg().load(Main.getF());
						Main.getCfg().set("Version", Main.getV());
						Main.loadYml();
						Mob.killAll();
						p.sendMessage("§c[Mobs]§f配置重载成功.");
					} catch (Exception e) {
						e.printStackTrace();
						p.sendMessage("§c[Mobs]§f配置重载失败,您可以尝试删除配置重启服务器重新生成配置文件.");
					}
				} else {
					return false;
				}
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				if (args.length == 1) {
					p.sendMessage("§c[Mobs]§f/Mobs help [main/mob/skill/spawn] (spawn)<Point/World> 查看[主命令/怪物命令/技能命令/刷新点命令]的帮助文本");
					return true;
				}
				if (args.length != 2) {
					if (args.length > 2 & args[1].equalsIgnoreCase("spawn"))
						;
					else
						return false;
				}
				if (args[1].equalsIgnoreCase("main")) {
					mainHelp(p);
				} else if (args[1].equalsIgnoreCase("mob")) {
					mobHelp(p);
				} else if (args[1].equalsIgnoreCase("skill")) {
					skillHelp(p);
				} else if (args[1].equalsIgnoreCase("spawn")) {

					if (args.length == 2) {
						p.sendMessage("§c[Mobs]§f/Mobs help spawn <Point/World> 查看[刷新点命令]的帮助文本");
						return true;
					}
					if (!args[2].equalsIgnoreCase("point")
							& !args[2].equalsIgnoreCase("world")) {
						p.sendMessage("§c[Mobs]§f/Mobs help spawn <Point/World> 查看[刷新点命令]的帮助文本");
						return true;
					}
					// 所有刷新点

					spawnHelp(p);
					if (args[2].equalsIgnoreCase("point")) {
						pointSpawnHelp(p);
					}
					if (args[2].equalsIgnoreCase("world")) {
						worldSpawnHelp(p);
					}

				} else {
					return false;
				}
				return true;

			} else if (args[0].equalsIgnoreCase("skill")) {
				return skill(args, p);
			} else {

				return false;
			}
		}

		return false;

	}

	public static void worldSpawnHelp(Player p) {
		p.sendMessage("§a  /Mobs spawn modify world [add/list/del] 修改刷新的世界");
		p.sendMessage("§a  /Mobs spawn modify chance [几率] 修改刷新的几率");
		p.sendMessage("§a  /Mobs spawn modify playerNearby [距离] 修改与玩家的刷新最大距离");

	}

	public static void pointSpawnHelp(Player p) {
		p.sendMessage("§a  /Mobs spawn modify point 设置刷新点位置");
		p.sendMessage("§a  /Mobs spawn modify single [Single] 设置每次刷新数量");
		p.sendMessage("§a  /Mobs spawn modify range [Range] 设置活动半径(超出会被弹回原点)");
		p.sendMessage("§a  /Mobs spawn modify center 设置活动半径的圆心点");

	}

	public static void spawnHelp(Player p) {
		p.sendMessage("§a  /Mobs spawn new [Point/World] [刷新点名] 创建某个刷新点(自动select)");
		p.sendMessage("§a  /Mobs spawn select [Point/World] [刷新点名] 设置某个刷新点的配置");
		p.sendMessage("§a  /Mobs spawn list [Point/World] 查看刷新点列表");
		p.sendMessage("§a  /Mobs spawn killall 删除一个刷新点的刷出怪物");
		p.sendMessage("§a  /Mobs spawn see [刷新点] 查看一个怪物刷新点的详细信息");
		p.sendMessage("§a  /Mobs spawn modify del [刷新点] 删除一个怪物刷新点");
		p.sendMessage("§a  /Mobs spawn modify mob [sName] 设置怪物模板");
		p.sendMessage("§a  /Mobs spawn modify lag [Lag] 设置刷新间隔(tick:20tick=1s)");
		p.sendMessage("§a  /Mobs spawn modify max [Max] 设置怪物最大数量(世界刷新为区块最大数量)");

	}

	public static void mobHelp(Player p) {
		p.sendMessage("§a  /Mobs mob new [怪物名(不是显示名字,做为记号)] 创建某个怪物(自动select)");
		p.sendMessage("§a  /Mobs mob select [怪物名] 设置某个怪物的配置");
		p.sendMessage("§a  /Mobs mob spawn 在视线处创建一个该怪物");
		p.sendMessage("§a  /Mobs mob see 查看一个怪物的详细信息");
		p.sendMessage("§a  /Mobs mob list  查看怪物列表");
		p.sendMessage("§a  /Mobs mob modify del 删除一个怪物");
		p.sendMessage("§a  /Mobs mob modify drop [add/list/del] 增加掉落物和掉落几率");
		p.sendMessage("§a  /Mobs mob modify droptype [方式(All,Invalid,Random)] 设置掉落方式");
		p.sendMessage("§a  /Mobs mob modify name [Name] 设置名称");
		p.sendMessage("§a  /Mobs mob modify hp [HighHP] <LowHP>设置血量");
		p.sendMessage("§a  /Mobs mob modify attrcover [boolean] 设置是否覆盖属性");
		p.sendMessage("§a  /Mobs mob modify damage [HighDamage] <LowDamage> 设置伤害");
		p.sendMessage("§a  /Mobs mob modify exp [HighEXP] <LowEXP> 设置死亡掉落的经验");
		p.sendMessage("§a  /Mobs mob modify type [Type] 设置怪物类型");
		p.sendMessage("§a  /Mobs mob modify effect [set:([药水类型] [药水等级])/del/list] 增加怪物的永久药水状态");
		p.sendMessage("§a  /Mobs mob modify rider [sName] 让这个怪物附带一个驾驶者(其他的怪物模板)");
		p.sendMessage("§a  /Mobs mob modify eqpt 设置装备为当前穿戴的装备和手拿的武器");
		p.sendMessage("§a  /Mobs mob modify skill [add/list/del] 增删查技能列表");
		p.sendMessage("§a  /Mobs mob modify sl [Day/Night/Sun/Rain/Thunder] [true/false] 设置怪物刷新对环境的需求");

	}

	public static void skillHelp(Player p) {
		p.sendMessage("§a  /Mobs skill new [技能名(不是显示名字,做为记号)] [技能类型] 创建某个技能(自动select)");
		p.sendMessage("§a  /Mobs skill select [技能名] 设置某个技能的配置");
		p.sendMessage("§a  /Mobs skill type  查看技能类型列表");
		p.sendMessage("§a  /Mobs skill list <技能类型> 查看技能列表");
		p.sendMessage("§a  /Mobs skill see 查看一个技能的详细信息");
		p.sendMessage("§a  /Mobs skill help [技能类型] 查看该类型技能包含的子指令");
		p.sendMessage("§a  /Mobs skill modify  修改技能属性");
		Skill.mainHelp();
	}

	public static void mainHelp(Player p) {
		p.sendMessage("§a  /Mobs set 设置选择器的物品ID为手上的物品");
		p.sendMessage("§a  /Mobs spawn 设置刷新点的各种属性<重要命令>");
		p.sendMessage("§a  /Mobs mob 设置怪物的各种属性<重要命令>");
		p.sendMessage("§a  /Mobs skill 设置技能的各种属性<重要命令>");
		p.sendMessage("§a  /Mobs setBan [Animal/Monster] [true/false] 设置你所在的世界是否禁用默认产生的动物/怪物");
		p.sendMessage("§a  /Mobs listBan 查看你所在的世界是否禁用默认产生的动物/怪物");
		p.sendMessage("§a  /Mobs listPotionEffectType 查看所有支持的药水类型");
		p.sendMessage("§a  /Mobs listEntityType 查看所有支持作为怪物的类型");
		p.sendMessage("§a  /Mobs reload 重载插件");
		p.sendMessage("§a  /Mobs help 帮助");

	}

	private static boolean skill(String[] args, Player p) {

		if (args[1].equalsIgnoreCase("new")) {
			if (args.length != 4)
				return false;

			if (Skill.isSkill(args[2]) != -1) {
				p.sendMessage("§c[Mobs]§f名称已存在.");
				return true;
			}

			Skill skill = Skill.newSkill(args[3], args[2]);
			if (skill == null) {
				p.sendMessage("§c[Mobs]§f该技能类型不存在,或程序内部错误.");
				return true;
			}
			p.sendMessage("§c[Mobs]§f操作成功.");
			Main.setsSkill(skill);
			try {
				Main.saveYml();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return true;

		} else if (args[1].equalsIgnoreCase("select")) {
			if (args.length != 3)
				return false;

			if (Skill.isSkill(args[2]) == -1) {
				p.sendMessage("§c[Mobs]§f该技能不存在.");
				return true;
			}

			Main.setsSkill(Skill.getSkills().get(Skill.isSkill(args[2])));
			p.sendMessage("§c[Mobs]§f选择成功.");
			return true;

		} else if (args[1].equalsIgnoreCase("type")) {
			p.sendMessage(Skill.getTypes());
			return true;
		} else if (args[1].equalsIgnoreCase("list")) {
			if (args.length == 2)
				p.sendMessage(Skill.getList());
			else if (args.length == 3)
				p.sendMessage(Skill.getList(args[2]));
			else
				return false;

			return true;
		} else if (args[1].equalsIgnoreCase("help")) {
			if (args.length != 3)
				return false;
			p.sendMessage(Skill.help(args[2]));
			return true;

		}

		else if (Main.getsSkill() == null) {
			p.sendMessage("§c[Mobs]§f请先选择一个技能.");
			return true;
		} else if (args[1].equalsIgnoreCase("see")) {
			p.sendMessage(Main.getsSkill().see());
			return true;
		} else if (args[1].equalsIgnoreCase("modify")) {
			return Main.getsSkill().cmdManager(args, p);

		}

		return false;
	}

	private static boolean mob(Player p, String[] args) {
		if (args[1].equalsIgnoreCase("select")) {
			if (args.length == 2) {
				p.sendMessage("§a  /Mobs mob select  [怪物名] 设置某个怪物的配置");
				return true;
			}
			if (args.length != 3) {
				return false;
			}

			ConfigurationSection section = Main.getCfg()
					.getConfigurationSection("MobModel")
					.getConfigurationSection(args[2]);
			if (section == null) {
				p.sendMessage("§c[Mobs]§f该怪物不存在.");
				return true;
			}
			Main.setsMobModel(new MobModel(section));
			p.sendMessage("§c[Mobs]§f已经选择怪物:" + args[2] + ".");
			return true;

		} else if (args[1].equalsIgnoreCase("new")) {
			if (args.length != 3)
				return false;

			if (MobModel.isMobModel(args[2]) != -1) {

				p.sendMessage("§c[Mobs]§f名称已存在.");

			} else {
				try {
					Main.setsMobModel(new MobModel(args[2], Main.getCfg()
							.getConfigurationSection("MobModel")));
					Main.saveYml();
					p.sendMessage("§c[Mobs]§f创建成功.");
				} catch (IOException e) {
					p.sendMessage("§c[Mobs]§f创建失败.");
				}
			}
			return true;

		} else if (args[1].equalsIgnoreCase("list")) {
			String s = "怪物列表:";
			for (int i = 0; i < MobModel.getMobModels().size(); i++) {
				s += MobModel.getMobModels().get(i).getsName();
				if (i != MobModel.getMobModels().size() - 1) {
					s += ",";
				}
			}
			p.sendMessage(s);
			return true;
		} else if (Main.getsMobModel() == null) {
			p.sendMessage("§c[Mobs]§f请先使用/Mobs mob select [怪物名] 选中某个怪物进行修改.");
			return true;
		}
		MobModel mm = Main.getsMobModel();

		if (args[1].equalsIgnoreCase("see")) {
			p.sendMessage(mm.getSee());
			return true;
		} else if (args[1].equalsIgnoreCase("spawn")) {
			if (args.length != 2)
				return false;

			mm.spawnMob(p.getEyeLocation());
			p.sendMessage("§c[Mobs]§f创建成功.");
			return true;

		} else if (args[1].equalsIgnoreCase("modify")) {
			return mobModify(p, args);
		}

		return false;
	}

	private static boolean mobModify(Player p, String[] args) {
		MobModel mm = Main.getsMobModel();
		if (args[2].equalsIgnoreCase("del")) {
			if (!mm.remove())
				p.sendMessage("§c[Mobs]§f该怪物不存在.");
			Main.setsMobModel(null);
			p.sendMessage("§c[Mobs]§f操作成功.");
		} else if (args[2].equalsIgnoreCase("drop")) {
			if (args.length < 4) {
				return false;
			}
			if (args[3].equalsIgnoreCase("add")) {

				if (p.getItemInHand() == null
						|| p.getItemInHand().getType().name()
								.equalsIgnoreCase(Material.AIR.name())) {
					p.sendMessage("§c[Mobs]§f请手持一件装备.");
					return true;
				}

				if (args.length != 5) {
					return false;
				}

				mm.addDrop(p.getItemInHand(), Integer.parseInt(args[4]));

			} else if (args[3].equalsIgnoreCase("list")) {

				String s = "掉落物列表:";
				ArrayList<DropItemStack> a = mm.getDrop();
				for (int i = 0; i < a.size(); i++) {
					s += i + ":[类型-" + a.get(i).getItem().getType().name()
							+ "|" + a.get(i).getI() + "%|"
							+ a.get(i).getItem().getAmount() + "个]";
					if (i != a.size() - 1) {
						s += ",";
					}
				}

				p.sendMessage(s);
				return true;

			} else if (args[3].equalsIgnoreCase("del")) {
				if (args.length != 5) {
					return false;
				}

				if (!mm.delDrop(Integer.parseInt(args[4]))) {
					p.sendMessage("§c[Mobs]§f该掉落物不存在.");
					return true;
				}
			}

		} else if (args[2].equalsIgnoreCase("sl")) {
			if (args.length != 5)
				return false;
			boolean t = false;
			if (args[4].equalsIgnoreCase("true"))
				t = true;
			else if (args[4].equalsIgnoreCase("false"))
				t = false;
			else
				return false;

			if (args[3].equalsIgnoreCase("Night")) {
				mm.getSurvivalLimit().isNight(t);
			} else if (args[3].equalsIgnoreCase("Rain")) {
				mm.getSurvivalLimit().isRain(t);
			} else if (args[3].equalsIgnoreCase("Thunder")) {
				mm.getSurvivalLimit().isThundering(t);
			} else if (args[3].equalsIgnoreCase("Day")) {
				mm.getSurvivalLimit().isDay(t);
			} else if (args[3].equalsIgnoreCase("Sun")) {
				mm.getSurvivalLimit().isSun(t);
			} else {
				return false;
			}

		} else if (args[2].equalsIgnoreCase("droptype")) {
			if (args.length != 4)
				return false;

			if (args[3].equalsIgnoreCase("All")) {
				mm.setDropType(1);
			} else if (args[3].equalsIgnoreCase("Invalid")) {
				mm.setDropType(0);
			} else if (args[3].equalsIgnoreCase("Random")) {
				mm.setDropType(2);
			} else
				return false;

		} else if (args[2].equalsIgnoreCase("effect")) {
			if (args.length < 4)
				return false;
			if (args[3].equalsIgnoreCase("set")) {
				if (args.length < 6)
					return false;

				if (PotionEffectType.getByName(args[4]) == null) {
					p.sendMessage("§c[Mobs]§f该药水类型不存在.");
					return true;
				} else {
					Main.getsMobModel().addPotionEffect(args[4],
							Integer.parseInt(args[5]));

				}

			} else if (args[3].equalsIgnoreCase("del")) {
				if (args.length < 5)
					return false;
				if (Main.getsMobModel().delPotionEffect(args[4]) == false) {
					p.sendMessage("§c[Mobs]§f该药水状态不存在.");
					return true;
				}

			} else if (args[3].equalsIgnoreCase("list")) {
				String str = "药水效果列表:";
				for (int i = 0; i < Main.getsMobModel().getPotionEffectList()
						.size(); i++) {
					if (i != 0)
						str += ",";

					str += (String) Main.getsMobModel().getPotionEffectList()
							.toArray()[i]
							+ ":"
							+ Main.getsMobModel()
									.getPotionEffectLv(
											(String) Main.getsMobModel()
													.getPotionEffectList()
													.toArray()[i]) + "级";
				}
				p.sendMessage(str);
				return true;

			} else
				return false;

		} else if (args[2].equalsIgnoreCase("rider")) {
			if (args.length != 4)
				return false;

			if (MobModel.isMobModel(args[3]) == -1) {
				p.sendMessage("§c[Mobs]§f该怪物不存在.");
				return true;
			}
			if (Main.getsMobModel().getsName()
					.equalsIgnoreCase(MobModel.getMobModel(args[3]).getsName())) {
				p.sendMessage("§c[Mobs]§f你不可以骑自己.");
				return true;
			}

			mm.setRider(args[3]);
		} else if (args[2].equalsIgnoreCase("name")) {
			if (args.length != 4)
				return false;
			mm.setDisplayName(args[3]);
		} else if (args[2].equalsIgnoreCase("damage")) {
			if (args.length == 4) {
				mm.setDmg(new Damage(Integer.parseInt(args[3])));
			} else if (args.length == 5) {
				mm.setDmg(new Damage(Integer.parseInt(args[3]), Integer
						.parseInt(args[4])));
			} else {
				return false;
			}

		} else if (args[2].equalsIgnoreCase("hp")) {
			if (args.length == 4) {
				mm.setHp(new HP(Integer.parseInt(args[3])));
			} else if (args.length == 5) {
				mm.setHp(new HP(Integer.parseInt(args[3]), Integer
						.parseInt(args[4])));
			} else {
				return false;
			}
		} else if (args[2].equalsIgnoreCase("attrcover")) {
			if (args.length != 4)
				return false;
			if (args[3].equalsIgnoreCase("true"))
				mm.setAttrCover(true);
			else if (args[3].equalsIgnoreCase("false"))
				mm.setAttrCover(false);
			else
				return false;

		} else if (args[2].equalsIgnoreCase("exp")) {
			if (args.length == 4) {
				mm.setExp(new EXP(Integer.parseInt(args[3])));
			} else if (args.length == 5) {
				mm.setExp(new EXP(Integer.parseInt(args[3]), Integer
						.parseInt(args[4])));
			} else {
				return false;
			}
		} else if (args[2].equalsIgnoreCase("type")) {
			if (args.length != 4)
				return false;
			mm.setType(EntityType.fromName(args[3]));
		} else if (args[2].equalsIgnoreCase("eqpt")) {
			mm.setEqpt(new Eqpt(p.getEquipment().getHelmet(), p.getEquipment()
					.getChestplate(), p.getEquipment().getLeggings(), p
					.getEquipment().getBoots(), p.getEquipment()
					.getItemInHand()));
			// 装备
		} else if (args[2].equalsIgnoreCase("skill")) {

			if (args.length < 4)
				return false;

			if (args[3].equalsIgnoreCase("add")) {

				if (args.length != 5)
					return false;
				Skill skill = Skill.getSkill(args[4]);
				if (skill == null) {

					p.sendMessage("§c[Mobs]§f该技能不存在.");
					return true;

				} else
					mm.addSkill(skill);

			} else if (args[3].equalsIgnoreCase("del")) {
				if (args.length != 5)
					return false;
				if (!mm.delSkills(Integer.parseInt(args[4]))) {
					p.sendMessage("§c[Mobs]§f该技能不存在.");
					return false;
				}

			} else if (args[3].equalsIgnoreCase("list")) {
				if (args.length != 4)
					return false;

				String str = "怪物技能列表:";
				for (int i = 0; i < mm.getSkills().size(); i++) {
					if (i != 0)
						str += ",";
					str += i + ":" + mm.getSkills().get(i).getsName();
				}
				p.sendMessage("str");
				return true;

			} else
				return false;

		} else {
			return false;
		}

		try {
			saveMobModel(mm, p);
			p.sendMessage("§c[Mobs]§f操作成功.");
		} catch (IOException e) {
			p.sendMessage("§c[Mobs]§f保存失败.");
		}
		return true;
	}

	private static boolean spawn(Player p, String[] args) {

		if (args[1].equalsIgnoreCase("select")) {
			if (args.length != 4) {
				return false;
			}
			ConfigurationSection section = null;
			if (args[2].equalsIgnoreCase("Point")) {
				section = Main.getCfg().getConfigurationSection("PointSpawn")
						.getConfigurationSection(args[3]);
				if (section == null) {
					p.sendMessage("§c[Mobs]§f该点不存在.");
					return true;
				}
				Main.setsSpawn(new PointSpawn(section));
				p.sendMessage("§c[Mobs]§f已经选择点:" + args[3] + ".");
				return true;
			} else if (args[2].equalsIgnoreCase("World")) {
				section = Main.getCfg().getConfigurationSection("WorldSpawn")
						.getConfigurationSection(args[3]);
				if (section == null) {
					p.sendMessage("§c[Mobs]§f该点不存在.");
					return true;
				}
				Main.setsSpawn(new WorldSpawn(section));
				p.sendMessage("§c[Mobs]§f已经选择点:" + args[3] + ".");
				return true;
			}
		} else if (args[1].equalsIgnoreCase("new")) {

			if (args.length != 4)
				return false;
			if (args[2].equalsIgnoreCase("Point")) {
				if (Main.getO() == null) {
					p.sendMessage("§c[Mobs]§f请先选择一个点.");
					return true;
				}

				if (PointSpawn.isPSpawn(args[3]) != -1) {
					p.sendMessage("§c[Mobs]§f名称已存在.");
					return true;
				}
				Main.setsSpawn(new PointSpawn(args[3], Main.getCfg()
						.getConfigurationSection("PointSpawn"), Main.getO()));
				Main.getsSpawn().save();
				p.sendMessage("§c[Mobs]§f创建成功.");
				try {
					Main.saveYml();
				} catch (IOException e) {
					p.sendMessage("§c[Mobs]§f保存失败.");
				}
				return true;

			} else if (args[2].equalsIgnoreCase("World")) {
				if (WorldSpawn.isWSpawn(args[3]) != -1) {
					p.sendMessage("§c[Mobs]§f名称已存在.");
					return true;
				}
				ArrayList<World> w = new ArrayList<World>();
				w.add(p.getWorld());
				Main.setsSpawn(new WorldSpawn(args[3], Main.getCfg()
						.getConfigurationSection("WorldSpawn"), w));
				Main.getsSpawn().save();
				p.sendMessage("§c[Mobs]§f创建成功.");
				try {
					Main.saveYml();
				} catch (IOException e) {
					p.sendMessage("§c[Mobs]§f保存失败.");
				}
				return true;
			}

		} else if (args[1].equalsIgnoreCase("list")) {
			if (args.length != 3)
				return false;

			String s = "";
			if (args[2].equalsIgnoreCase("world")) {
				s = "世界刷新点列表:";

				for (int i = 0; i < WorldSpawn.getWmobcreates().size(); i++) {
					s += WorldSpawn.getWmobcreates().get(i).getcName();
					if (i != WorldSpawn.getWmobcreates().size() - 1) {
						s += ",";
					}
				}

			} else if (args[2].equalsIgnoreCase("point")) {
				s = "独立刷新点列表:";

				for (int i = 0; i < PointSpawn.getPmobcreates().size(); i++) {
					s += PointSpawn.getPmobcreates().get(i).getcName();
					if (i != PointSpawn.getPmobcreates().size() - 1) {
						s += ",";
					}
				}

			}
			p.sendMessage(s);
			return true;

		} else if (Main.getsSpawn() == null) {
			p.sendMessage("§c[Mobs]§f请先使用/Mobs spawn select [Point/World] [刷新点名] 选中某个刷新点进行修改.");
			return true;
		}

		Spawn spawn = Main.getsSpawn();

		if (args[1].equalsIgnoreCase("modify")) {
			return spawnModify(p, args);
		} else if (args[1].equalsIgnoreCase("killall")) {

			Main.getsSpawn().killAll();
			p.sendMessage("§c[Mobs]§f执行成功.");
			return true;

		} else if (args[1].equalsIgnoreCase("see")) {
			p.sendMessage(spawn.getSee());
			return true;
		} else {
			return false;
		}

	}

	private static boolean spawnModify(Player p, String[] args) {
		if (args.length < 3) {
			return false;
		}
		if (Main.getsSpawn() instanceof PointSpawn) {
			PointSpawn pSpawn = (PointSpawn) Main.getsSpawn();
			if (args[2].equalsIgnoreCase("point")) {
				if (Main.getO() == null) {

					p.sendMessage("§c[Mobs]§f请先使用选择器选择一个点.");
					return true;
					// /
				}

				pSpawn.setP(Main.getO());
				p.sendMessage("§c[Mobs]§f您已经设置了新的刷新点.");
				saveSpawn(pSpawn, p);
				return true;
			} else if (args[2].equalsIgnoreCase("single")) {
				if (args.length != 4) {
					return false;
				}
				pSpawn.setOne(Integer.parseInt(args[3]));
				p.sendMessage("§c[Mobs]§f设置成功.");
				return true;

			} else if (args[2].equalsIgnoreCase("range")) {
				if (args.length != 4) {
					return false;
				}
				pSpawn.setRange(Integer.parseInt(args[3]));
				p.sendMessage("§c[Mobs]§f您已经设置了新的活动半径.");
				saveSpawn(pSpawn, p);
				return true;
			} else if (args[2].equalsIgnoreCase("center")) {
				if (Main.getO() == null) {

					p.sendMessage("§c[Mobs]§f请先使用选择器选择一个点.");
					return true;
					// /
				}
				pSpawn.setO(Main.getO());
				p.sendMessage("§c[Mobs]§f您已经设置了新的圆心点.");
				saveSpawn(pSpawn, p);
				return true;
			}

		} else if (Main.getsSpawn() instanceof WorldSpawn) {
			WorldSpawn wSpawn = (WorldSpawn) Main.getsSpawn();
			if (args[2].equalsIgnoreCase("world")) {
				if (args.length != 5) {
					return false;
				}
				if (args[3].equalsIgnoreCase("add")) {
					for (int i = 0; i < wSpawn.getWorld().size(); i++) {
						if (wSpawn.getWorld().get(i).getName()
								.equalsIgnoreCase(args[4])) {
							p.sendMessage("§c[Mobs]§f该世界已存在.");
							return true;
						}
					}
					if (Bukkit.getWorld(args[4]) != null) {
						wSpawn.getWorld().add(Bukkit.getWorld(args[4]));
						p.sendMessage("§c[Mobs]§f操作成功.");
					} else {
						p.sendMessage("§c[Mobs]§f该世界不存在.");
					}
				} else if (args[3].equalsIgnoreCase("del")) {
					for (int i = 0; i < wSpawn.getWorld().size(); i++) {
						if (wSpawn.getWorld().get(i).getName()
								.equalsIgnoreCase(args[4])) {
							wSpawn.getWorld().remove(i);
							p.sendMessage("§c[Mobs]§f操作成功.");
							return true;
						}
					}

					p.sendMessage("§c[Mobs]§f该世界不存在.");
					return true;
				} else if (args[3].equalsIgnoreCase("list")) {
					String s = "刷新世界列表:\n";
					for (int i = 0; i < wSpawn.getWorld().size(); i++) {
						s += wSpawn.getWorld().get(i).getName();
						if (i != wSpawn.getWorld().size() - 1) {
							s += ",";
						}
					}
					p.sendMessage(s);
				}
				p.sendMessage("§a  /Mobs spawn modify world [add/list/del] 修改刷新的世界");

				p.sendMessage("§c[Mobs]§f.");
				saveSpawn(wSpawn, p);
				return true;
			} else if (args[2].equalsIgnoreCase("chance")) {
				if (args.length != 4) {
					return false;
				}
				wSpawn.setChance(Double.parseDouble(args[3]));
				p.sendMessage("§c[Mobs]§f您已经设置了新的刷新几率.");
				saveSpawn(wSpawn, p);
				return true;
			} else if (args[2].equalsIgnoreCase("playerNearby")) {
				if (args.length != 4) {
					return false;
				}

				wSpawn.setPlayerNearby(Integer.parseInt(args[3]));
				p.sendMessage("§c[Mobs]§f设置成功.");
				saveSpawn(wSpawn, p);
				return true;

			}

		}

		// /以下为共有命令
		Spawn spawn = Main.getsSpawn();
		if (args[2].equalsIgnoreCase("del")) {
			spawn.remove();
			Main.setsSpawn(null);
			p.sendMessage("§c[Mobs]§f您已经删除了该点.");
		} else if (args[2].equalsIgnoreCase("lag")) {
			if (args.length != 4) {
				return false;
			}
			spawn.setTime(Integer.parseInt(args[3]));
			p.sendMessage("§c[Mobs]§f设置成功.");
		} else if (args[2].equalsIgnoreCase("max")) {
			if (args.length != 4) {
				return false;
			}
			spawn.setAll(Integer.parseInt(args[3]));
			p.sendMessage("§c[Mobs]§f设置成功.");
		} else if (args[2].equalsIgnoreCase("Mob")) {

			if (args.length != 4) {
				return false;
			}
			if (MobModel.isMobModel(args[3]) == -1) {
				p.sendMessage("§c[Mobs]§f怪物不存在.");
				return true;
			}
			Main.getsSpawn().setMobModel(
					MobModel.getMobModels().get(MobModel.isMobModel(args[3])));
			p.sendMessage("§c[Mobs]§f设置成功.");
		} else
			return false;

		saveSpawn(spawn, p);
		return true;
	}

	private static void saveSpawn(Spawn pSpawn, Player p) {
		pSpawn.save();
		try {
			Main.getCfg().save(Main.getF());
		} catch (IOException e) {
			p.sendMessage("§c[Mobs]§f配置保存失败.");
		}

	}

	private static void saveMobModel(MobModel mm, Player p) throws IOException {
		mm.save();
		try {
			Main.getCfg().save(Main.getF());
		} catch (IOException e) {
			p.sendMessage("§c[Mobs]§f配置保存失败.");
		}

	}
}
