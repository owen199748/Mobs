package cn.rpgmc.bean.skill;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import cn.rpgmc.bean.mob.Mob;


public abstract class Skill {
	private static String  type = null;
	private ConfigurationSection cfg = null;
	private double chance=0;
	public static final String TRIGGER_CYCLE="TRIGGER_CYCLE";//周期
	public static final String TRIGGER_ATTACK="TRIGGER_ATTACK";//攻击
	public static final String TRIGGER_HURT="TRIGGER_HURT";//受到伤害
	public static final String TRIGGER_DYING="TRIGGER_DYING";//濒死
	public static final String TRIGGER_TARGET="TRIGGER_TARGET";//瞄准
	private String trigger = TRIGGER_CYCLE;
	public static final String RANGE_WORLD="RANGE_WORLD";
	public static final String RANGE_TARGET="RANGE_TARGET";
	public static final String RANGE_CHUNK="RANGE_CHUNK";
	private String  range = RANGE_TARGET;
	private ArrayList<String> enemys=  new ArrayList<String>();
public static String getType() {
	return type;
}
protected static void setType(String type) {
	Skill.type = type;
}
	public String getRange() {
		return range;
	}
	public ArrayList<String> getEnemys() {
		return enemys;
	
	}
	private Skill(){
		
	}
	public Skill(String sName,ConfigurationSection cfg) {
	cfg.createSection(sName);
	this.cfg=cfg.getConfigurationSection(sName);
		trigger=TRIGGER_ATTACK;
		chance=25;
		range=RANGE_TARGET;
save();
	}
public Skill(ConfigurationSection cfg) {
	this.cfg=cfg;
	trigger=cfg.getString("trigger");
	chance=cfg.getInt("chance");
	range=cfg.getString("range");
	type=cfg.getString("type");
	enemys=(ArrayList<String>) cfg.getList("enemys");
}
public void save(){
	cfg.set("trigger",trigger);
	cfg.set("chance",chance);
	cfg.set("range",range);
	cfg.set("enemys",enemys);
	cfg.set("type", type);
	
}
public static Skill loadSkill(ConfigurationSection cfg){	String t = cfg.getString("type");
	if(t.equalsIgnoreCase(Skill_Message.getType()))
	{
		return new Skill_Message(cfg);
	}else if(t.equalsIgnoreCase(Skill_Package.getType()))
	{
		return new Skill_Package(cfg);
	}
	
	
	
	return null;
}
	public boolean isEnemy(String enemy) {
	
			try {
				
				for(int i=0;i<enemys.size();i++){
				if(Class.forName(enemys.get(i)).isInstance(Class.forName(enemy))){
					return true;
				}
			}
			} catch (ClassNotFoundException e) {

			}

		return false;
		
	}
	
	public boolean isEnemy(Class en) {
		
		try {
			
			for(int i=0;i<enemys.size();i++){
			if(Class.forName(enemys.get(i)).isInstance(en)){
				return true;
			}
		}
		} catch (ClassNotFoundException e) {

		}
if(enemys.size()==0){
	return true;
}
	return false;
	
}
	
	public ConfigurationSection getCfg() {
		return cfg;
	}

	public boolean addEnemys(String enemy) {
		if(enemys ==null)
			enemys=new ArrayList<String>();

		try {
			if(Class.forName(enemy)!=null)
				if(Class.forName(enemy).isInstance(Entity.class))
				enemys.add(enemy);
				else
					return false;
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	
	public void setRange(String range) {
		this.range = range;
	}
	public String getTrigger() {
		return trigger;
	}
	public double getChance() {
		return chance;
	}
	public void setChance(double chance) {
		this.chance = chance;
	}
	
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	public void runSkill(List<Entity> e) {
		if(chance>(Math.random()*100)){
			for(int i =0;i<e.size();i++){
				if(isEnemy(e.get(i).getClass()))
				run(e.get(i));
			}
		
		}
		
	}
	protected abstract void run(Entity entity);


}
