package cn.rpgmc.bean.skill;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;

public abstract class Skill_Package extends Skill {
	public Skill_Package(ConfigurationSection cfg) {
		super(cfg);
		// TODO 自动生成的构造函数存根
	}

	private ArrayList<Skill> skills = new ArrayList<Skill>();

}
