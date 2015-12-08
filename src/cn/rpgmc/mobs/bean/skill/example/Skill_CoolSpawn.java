package cn.rpgmc.mobs.bean.skill.example;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cn.rpgmc.mobs.bean.mob.Mob;
import cn.rpgmc.mobs.bean.skill.Skill;

public abstract class Skill_CoolSpawn extends Skill {

	private String msg;

	public Skill_CoolSpawn() {
	}

	public Skill_CoolSpawn(ConfigurationSection cfg) {
		super(cfg);
	}

	public Skill_CoolSpawn(String sName2, ConfigurationSection cfg2) {
		super(sName2, cfg2);
	}

	@Override
	public String getType() {
		return "Message";
	}

	@Override
	public String help() {
		return "技能类型:对话技能\n"
				+ "技能介绍:触发技能时进行对话.\n"
				+ "指令:\n"
				+ "  /mobs skill modify msg [对话内容] \n(其中对话内容的%a%代表释放技能对象,%b%代表被对话的玩家对象,%x1%,%y1%,%z1%,%w1%,%x2%,%y2%,%z2%,%w2%分别代表怪物和被对话的玩家位置.)";
	}

	@Override
	protected void skillNext(ConfigurationSection cfg) {
		this.msg = cfg.getString("msg");
	}

	@Override
	protected void newSkillNext() {
		this.msg = "";
	}

	@Override
	protected void saveNext() {
		getCfg().set("msg", this.msg);

	}

	@Override
	public String seeNext() {
		return "  对话内容:" + this.msg + "\n";
	}

	@Override
	public boolean isAuto() {
		return true;
	}

	@Override
	protected boolean cmdElse(String[] args, Player p) {
		if (args[0].equalsIgnoreCase("msg")) {
			if (args.length != 2)
				return false;
			msg = args[1];
			return true;

		}

		return false;
	}

	public void run(Mob mob, Entity[] es, Event event) {
		for (int i = 0; i < es.length; i++)
			this.run(mob, es[i], event);
	}

	public void run(Mob mob, Entity entity, Event event) {
		if (entity instanceof Player)
			((Player) entity).sendMessage(msg
					.replaceAll("%a%",
							((LivingEntity) mob.getE()).getCustomName())
					.replaceAll("%b%", ((Player) entity).getDisplayName())
							.replaceAll("x1",
									mob.getE().getLocation().getBlockX() + "")
							.replaceAll("y1",
									mob.getE().getLocation().getBlockY() + "")
							.replaceAll("z1",
									mob.getE().getLocation().getBlockZ() + "")
							.replaceAll("w1", mob.getE().getWorld().getName())
							.replaceAll("x2",
									entity.getLocation().getBlockX() + "")
							.replaceAll("y2",
									entity.getLocation().getBlockY() + "")
							.replaceAll("z2",
									entity.getLocation().getBlockZ() + "")
							.replaceAll("w2", entity.getWorld().getName())
							.replaceAll("&", "§"));

	}

	@Override
	public boolean canTriggerToCycle() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canTriggerToAttack() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canTriggerToHurt() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canTriggerToDying() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canTriggerToTarget() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canTriggerToBeTarget() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canTriggerToBeSpawn() {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean canRangeToWorld() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canRangeToTarget() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canRangeToChunk() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean canRangeToNearby() {
		// TODO 自动生成的方法存根
		return false;
	}

}
