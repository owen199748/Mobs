package pw.owen.mobs.command.example;

import org.bukkit.entity.Player;

import pw.owen.mobs.bean.skill.Skill;
import pw.owen.mobs.command.PluginCommand;
import pw.owen.mobs.utils.Send;

public class Help_CMD implements PluginCommand {

	@Override
	public String[] getBranch() {
		// TODO 自动生成的方法存根
		return new String[] { "mobs", "help" };
	}

	@Override
	public boolean run(Player p, String[] args, String auto) throws Exception {

		if (args.length == 0) {
			Send.sendPluginMessage(
					p,
					"/Mobs help [main/mob/skill/spawn] (spawn)<Point/World> 查看[主命令/怪物命令/技能命令/刷新点命令]的帮助文本");
					return true;
				}

		if (args.length != 1)
			if (!(args.length > 1 & args[0].equalsIgnoreCase("spawn")))
						return false;


		if (args[0].equalsIgnoreCase("main")) {
					mainHelp(p);
		} else if (args[0].equalsIgnoreCase("mob")) {
					mobHelp(p);
		} else if (args[0].equalsIgnoreCase("skill")) {
					skillHelp(p);
		} else if (args[0].equalsIgnoreCase("spawn")) {

			if (args.length == 1) {
				Send.sendPluginMessage(p,
						"/Mobs help spawn <Point/World> 查看[刷新点命令]的帮助文本");
						return true;
					}
			if (!args[1].equalsIgnoreCase("point")
					& !args[1].equalsIgnoreCase("world")) {
				Send.sendPluginMessage(p,
						"/Mobs help spawn <Point/World> 查看[刷新点命令]的帮助文本");
						return true;
					}
					// 所有刷新点

					spawnHelp(p);
			if (args[1].equalsIgnoreCase("point")) {
						pointSpawnHelp(p);
					}
			if (args[1].equalsIgnoreCase("world")) {
						worldSpawnHelp(p);
					}

				} else {
					return false;
		}
		return true;

	}


	public static void worldSpawnHelp(Player p) {
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify world [add/list/del] 修改刷新的世界");
		Send.sendPluginMessage(p, "&3  /Mobs spawn modify chance [几率] 修改刷新的几率");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify playerNearby [距离] 修改与玩家的刷新最大距离");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify maxInChunk [数量] 修改在当前区块的最大数量");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify maxInWorld [数量] 修改在当前世界的最大数量");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify maxInServer [数量] 修改在当前服务器的最大数量");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify onPlayerNum [true/false] 刷新几率是否考虑玩家数量");

	}

	public static void pointSpawnHelp(Player p) {
		Send.sendPluginMessage(p, "&3  /Mobs spawn tp 传送到这个刷怪点处");
		Send.sendPluginMessage(p, "&3  /Mobs spawn modify point 设置刷新点中心位置");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify single [Single] 设置每次刷新数量");
		Send.sendPluginMessage(p,
 "&3  /Mobs spawn modify max [Max] 设置怪物最大数量");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify range [Range] 设置刷新半径(如果onMove为True超出会被弹回原点)");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify onPoint [true/false] 设置是否只在刷新点上刷新");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify onMove [true/false] 设置是否只在刷新范围内活动");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify copyTo [新刷新点名] 复制当前刷新点到新的名字和位置");

	}

	public static void spawnHelp(Player p) {
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn new [Point/World] [刷新点名] 创建某个刷新点(自动select)");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn select [Point/World] [刷新点名] 设置某个刷新点的配置");
		Send.sendPluginMessage(p, "&3  /Mobs spawn list [Point/World] 查看刷新点列表");
		Send.sendPluginMessage(p, "&3  /Mobs spawn killall 删除一个刷新点的刷出怪物");
		Send.sendPluginMessage(p, "&3  /Mobs spawn see [刷新点] 查看一个怪物刷新点的详细信息");
		Send.sendPluginMessage(p, "&3  /Mobs spawn modify del [刷新点] 删除一个怪物刷新点");
		Send.sendPluginMessage(p, "&3  /Mobs spawn modify mob [sName] 设置怪物模板");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify lag [Lag] 设置刷新间隔(tick:20tick=1s)");
		Send.sendPluginMessage(p,
				"&3  /Mobs spawn modify copy [新刷新点名] 复制当前刷新点到新的名字");

	}

	public static void mobHelp(Player p) {
		Send.sendPluginMessage(p,
				"&3  /Mobs mob new [怪物名(不是显示名字,做为记号)] 创建某个怪物(自动select)");
		Send.sendPluginMessage(p, "&3  /Mobs mob select [怪物名] 设置某个怪物的配置");
		Send.sendPluginMessage(p, "&3  /Mobs mob spawn 在视线处创建一个该怪物");
		Send.sendPluginMessage(p, "&3  /Mobs mob see 查看一个怪物的详细信息");
		Send.sendPluginMessage(p, "&3  /Mobs mob list  查看怪物列表");
		Send.sendPluginMessage(p, "&3  /Mobs mob killall 移除该怪物类型的所有实体");
		Send.sendPluginMessage(p, "&3  /Mobs mob modify del 删除一个怪物");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify drop [add [几率] <最大数量> <最小数量>(不填数量默认为手持的堆叠数)/list/del [掉落物编号]] 增加掉落物和掉落几率");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify droptype [方式(All,Invalid,Random)] 设置掉落方式");
		Send.sendPluginMessage(p, "&3  /Mobs mob modify name [Name] 设置名称");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify hp [HighHP] <LowHP>设置血量");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify copy [新怪物模板名] 复制当前怪物模板到新的名字");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify attrcover [boolean] 设置是否覆盖属性");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify autoSave [boolean] 设置重启之后是否保存该怪物");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify noRepel [boolean] 设置是否不被击退");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify noNatureDamage [boolean] 设置是否取消自然伤害(掉落,窒息等)");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify damage [HighDamage] <LowDamage> 设置伤害");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify exp [HighEXP] <LowEXP> 设置死亡掉落的经验");
		Send.sendPluginMessage(p, "&3  /Mobs mob modify type [Type] 设置怪物类型");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify potion [set:([药水类型] [药水等级])/del [药水名称]/list] 增加怪物的永久药水状态");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify rider [sName] 让这个怪物附带一个驾驶者(其他的怪物模板)");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify eqpt 设置装备为当前穿戴的装备和手拿的武器");
		Send.sendPluginMessage(p,
				"&3  /Mobs mob modify skill [add/list/del [技能编号]] 增删查技能列表");
		Send.sendPluginMessage(
				p,
				"&3  /Mobs mob modify sl [Day/Night/Sun/Rain/Thunder] [true/false] 设置怪物刷新对环境的需求");
		Send.sendPluginMessage(
				p,
				"&3  /Mobs mob modify bossname [enable/show/nearby] 是否以boss方式显示名称(nearby属性中,0代表boss所在世界全部玩家,-1代表所有玩家 )");

	}

	public static void skillHelp(Player p) {
		Send.sendPluginMessage(p,
				"&3  /Mobs skill new [技能名(不是显示名字,做为记号)] [技能类型] 创建某个技能(自动select)");
		Send.sendPluginMessage(p, "&3  /Mobs skill select [技能名] 设置某个技能的配置");
		Send.sendPluginMessage(p, "&3  /Mobs skill type  查看技能类型列表");
		Send.sendPluginMessage(p, "&3  /Mobs skill list <技能类型> 查看技能列表");
		Send.sendPluginMessage(p, "&3  /Mobs skill see 查看一个技能的详细信息");
		Send.sendPluginMessage(p, "&3  /Mobs skill help [技能类型] 查看该类型技能包含的子指令");
		Send.sendPluginMessage(p, "&3  /Mobs skill modify  修改技能属性");
		Skill.mainHelp();
	}

	public static void mainHelp(Player p) {
		Send.sendPluginMessage(p, "&3  /Mobs set 设置选择器的物品ID为手上的物品");
		Send.sendPluginMessage(p, "&3  /Mobs spawn 设置刷新点的各种属性<重要命令>");
		Send.sendPluginMessage(p, "&3  /Mobs mob 设置怪物的各种属性<重要命令>");
		Send.sendPluginMessage(p, "&3  /Mobs skill 设置技能的各种属性<重要命令>");
		Send.sendPluginMessage(p, "&3  /Mobs killall 移除插件产生的所有实体");
		Send.sendPluginMessage(p,
				"&3  /Mobs setBan [Animal/Monster] [true/false] 设置你所在的世界是否禁用默认产生的动物/怪物");
		Send.sendPluginMessage(p, "&3  /Mobs listBan 查看你所在的世界是否禁用默认产生的动物/怪物");
		Send.sendPluginMessage(p, "&3  /Mobs PotionType 查看所有支持的药水类型");
		Send.sendPluginMessage(p, "&3  /Mobs MobType 查看所有支持作为怪物的类型");
		Send.sendPluginMessage(p, "&3  /Mobs update 智能升级旧版配置");
		Send.sendPluginMessage(p, "&3  /Mobs reload 重载插件");
		Send.sendPluginMessage(p, "&3  /Mobs help 帮助");

	}

}
