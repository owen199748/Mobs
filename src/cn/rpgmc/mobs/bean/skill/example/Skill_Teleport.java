package cn.rpgmc.mobs.bean.skill.example;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cn.rpgmc.mobs.bean.mob.Mob;
import cn.rpgmc.mobs.bean.skill.Skill;
import cn.rpgmc.mobs.utils.Calc;

public class Skill_Teleport extends Skill {

	private String world;
	private String x;
	private String y;
	private String z;

	public Skill_Teleport() {
	}

	public Skill_Teleport(ConfigurationSection cfg) {
		super(cfg);
	}

	public Skill_Teleport(String sName2, ConfigurationSection cfg2) {
		super(sName2, cfg2);
	}

	@Override
	public String getType() {
		return "Teleport";
	}

	@Override
	public String help() {
		return "技能类型:传送技能\n"
				+ "技能介绍:对目标进行传送.\n"
				+ "指令:\n"
				+ "  /mobs skill modify loc [x] [y] [z] [world] \n(其中x的%x1%代表使用技能者的x坐标,%x2%代表被施放者的x坐标(支持公式,例如:%x1%+(%x1%-%x2%)/3.),以此类推.world中的%w%代表所在世界)";
	}

	@Override
	protected void skillNext(ConfigurationSection cfg) {
		if (cfg.getConfigurationSection("loc") == null)
			cfg.createSection("loc");

		ConfigurationSection locm = cfg.getConfigurationSection("loc");
		if (locm.getString("X") != null)
			if (locm.getString("Y") != null)
				if (locm.getString("Z") != null)
					if (locm.getString("World") != null) {
						this.world = locm.getString("World");
						this.x = locm.getString("X");
						this.y = locm.getString("Y");
						this.z = locm.getString("Z");
					}
	}

	@Override
	protected void newSkillNext() {
		this.world = "";
		this.x = "";
		this.y = "";
		this.z = "";
	}

	@Override
	protected void saveNext() {
		if (getCfg().getConfigurationSection("loc") == null)
			getCfg().createSection("loc");
		getCfg().getConfigurationSection("loc").set("World", world);
		getCfg().getConfigurationSection("loc").set("X", x);
		getCfg().getConfigurationSection("loc").set("Y", y);
		getCfg().getConfigurationSection("loc").set("Z", z);
	}

	@Override
	public String seeNext() {
		return "  传送位置:\n" + "  World:" + world + "\n" + "  X:" + x + "\n"
				+ "  Y:" + y + "\n" + "  Z:" + z + "\n";
	}

	@Override
	public boolean isAuto() {
		return true;
	}

	@Override
	protected boolean cmdElse(String[] args, Player p) {

		if (args[0].equalsIgnoreCase("loc")) {
			if (args.length != 5)
				return false;

			this.world = args[4];
			this.x = args[1];
			this.y = args[2];
			this.z = args[3];
			return true;
		}

		return false;
	}

	public void run(Mob mob, Entity[] es, Event event) {
		for (int i = 0; i < es.length; i++)
			this.run(mob, es[i], event);
	}

	public void run(Mob mob, Entity entity, Event event) {
		World w = Bukkit.getWorld(world.replaceAll("%w%", entity.getWorld()
				.getName()));
		Double X = Calc.calc(x.replaceAll("%x1%",
				String.valueOf(mob.getE().getLocation().getX())).replaceAll(
				"%x2%", String.valueOf(entity.getLocation().getX())));
		Double Y = Calc.calc(y.replaceAll("%y1%",
				String.valueOf(mob.getE().getLocation().getY())).replaceAll(
				"%y2%", String.valueOf(entity.getLocation().getY())));
		Double Z = Calc.calc(z.replaceAll("%z1%",
				String.valueOf(mob.getE().getLocation().getZ())).replaceAll(
				"%z2%", String.valueOf(entity.getLocation().getZ())));
		if (w != null)
			if (X != null)
				if (Y != null)
					if (Z != null)
						entity.teleport(new Location(w, X, Y, Z));
	}

	@Override
	public boolean canTriggerToCycle() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canTriggerToAttack() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canTriggerToHurt() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canTriggerToDying() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canTriggerToTarget() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canTriggerToBeTarget() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canTriggerToBeSpawn() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canRangeToWorld() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canRangeToTarget() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canRangeToChunk() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canRangeToNearby() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canRangeToPlayer() {
		// TODO 自动生成的方法存根
		return true;
	}

}
