package cn.rpgmc.bean.skill;

import java.util.ArrayList;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

public class Skill_Package extends Skill{
private ArrayList<Skill> skills=new ArrayList<Skill>();
	public Skill_Package(ConfigurationSection cfg) {
		super(cfg);
		// TODO 自动生成的构造函数存根
		
	}
	@Override
	public void save() {
		// TODO 自动生成的方法存根
		super.save();
	}
	

	@Override
	protected void run(Entity entity) {
		// TODO 自动生成的方法存根
		
	}

}
