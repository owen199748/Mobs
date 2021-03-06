package pw.owen.mobs.bean.skill.example;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import pw.owen.mobs.bean.mob.Mob;
import pw.owen.mobs.bean.skill.Skill;

public abstract class Skill_AttributeModify extends Skill {

	private String msg;

	public Skill_AttributeModify() {
	}

	public Skill_AttributeModify(ConfigurationSection cfg) {
		super(cfg);
	}

	public Skill_AttributeModify(String sName2, ConfigurationSection cfg2) {
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
				+ "  /mobs skill modify msg [对话内容] \n(其中对话内容的%a%代表释放技能对象,%b%代表被对话的玩家对象)";
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

	@Override
	public void run(Mob mob, Entity[] entity, Event event) {

	}

}
