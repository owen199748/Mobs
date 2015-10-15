package cn.rpgmc.bean.skill;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.run.Main;

/**
 * 
 * @author owen
 * @see 
 *继承该类需要重写的方法:<P/>
 *static String getType() <P/>
 *static String help() <P/>
 */


public abstract class Skill {
	private String  sName = null;
	private static ArrayList<Class>  types= new ArrayList<Class> ();
	private static ArrayList<Skill>  skills = new ArrayList<Skill> ();
	private ConfigurationSection cfg = null;
	private double chance=0;
	private int cooling = 0;
	private static long BECOOLING = 0;
	public static final String TRIGGER_CYCLE="TRIGGER_CYCLE";//周期
	public static final String TRIGGER_ATTACK="TRIGGER_ATTACK";//攻击
	public static final String TRIGGER_HURT="TRIGGER_HURT";//受到伤害
	public static final String TRIGGER_DYING="TRIGGER_DYING";//濒死
	public static final String TRIGGER_TARGET="TRIGGER_TARGET";//瞄准
	public static final String TRIGGER_BETARGET="TRIGGER_BETARGET";//被瞄准
	private String trigger = TRIGGER_CYCLE;
	public static final String RANGE_WORLD="RANGE_WORLD";
	public static final String RANGE_TARGET="RANGE_TARGET";
	public static final String RANGE_CHUNK="RANGE_CHUNK";
	private String  range = RANGE_CHUNK;
	private ArrayList<String> enemys=  new ArrayList<String>();
	static {
/**
 * 在此添加所有继承Skill的类
 */
		types.add(Skill_Message.class);

		
	}
	public static void setSkills(ArrayList<Skill> skills) {
		Skill.skills = skills;
	}
	public String getsName() {
		return sName;
	}
	public static ArrayList<Skill> getSkills() {
		return skills;
	}
	public static void addSkills(Skill skill) {
		for(int i=0;i<skills.size();i++){
			if(skills.get(i).getsName().equalsIgnoreCase(skill.getsName())){
				skills.set(i, skill);
				return;
			}
			
		}
		skills.add(skill);
	}
	public static Skill newSkill(String tt,String sName){
		Class t = null;
		try {
			for(int i=0;i<types.size();i++){
				
			Method method	= types.get(i).getMethod("getType");
				String str=(String) method.invoke(null);
			if(str.equalsIgnoreCase(tt)){
				
				t=types.get(i);
				
				
				
			}
			
			
			
			
			
			}
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	if(t==null)
		return null;
	
	try {
		return (Skill) t.getDeclaredConstructor(String.class,ConfigurationSection.class).newInstance(sName,Main.getCfg().getConfigurationSection("Skill"));
	} catch (Exception e) {
		 if(e instanceof InvocationTargetException){  
             try {
				throw ((InvocationTargetException) e).getTargetException();
			} catch (Throwable e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}  
         }
		e.printStackTrace();
		return null;

	}
		
		

		
		
	}
	public static boolean isSkills(Skill skill) {
		for(int i=0;i<skills.size();i++){
			if(skills.get(i)==skill){
				
				return true;
			}
			
		}
		return false;
	}


public int getCooling() {
	return cooling;
}
public void setCooling(int cooling) {
	this.cooling = cooling;
}

public static boolean isTrigger(String trigger) {
	if(!trigger.equalsIgnoreCase(TRIGGER_CYCLE))
		if(!trigger.equalsIgnoreCase(TRIGGER_ATTACK))
			if(!trigger.equalsIgnoreCase(TRIGGER_HURT))
				if(!trigger.equalsIgnoreCase(TRIGGER_DYING))
					if(!trigger.equalsIgnoreCase(TRIGGER_TARGET))
						if(!trigger.equalsIgnoreCase(TRIGGER_BETARGET))
			return false;
	return true;
}
public static boolean isRange(String range) {
	if(!range.equalsIgnoreCase(RANGE_WORLD))
		if(!range.equalsIgnoreCase(RANGE_TARGET))
			if(!range.equalsIgnoreCase(RANGE_CHUNK))
				return false;
return true;
}
public boolean cmdManager(String[] args,Player p) {
	
		
		if(args[2].equalsIgnoreCase("chance")){
			if(args.length!=4)
				return false;
			setChance(Double.parseDouble(args[3]));
			
		}else if(args[2].equalsIgnoreCase("cooling")){
			if(args.length!=4)
				return false;
			setCooling(Integer.parseInt(args[3]));
			
		}else if(args[2].equalsIgnoreCase("trigger")){
			if(args.length!=4)
				return false;
			if(!isTrigger(args[3]))
				{p.sendMessage("§c[怪物生成器]§f触发类型不存在.");
				return false;
				}
				setTrigger(args[3]);
			
			
		}else if(args[2].equalsIgnoreCase("range")){
			if(args.length!=4)
				return false;
			if(!isRange(args[3]))
				{p.sendMessage("§c[怪物生成器]§f触发类型不存在.");
			return false;
			
				}
			
				setRange(args[3]);
			
			
		}else if(args[2].equalsIgnoreCase("enemys")){
			if(args.length<4)
				return false;
			
			if(args[3].equalsIgnoreCase("add")){
				if(args.length!=5)
					return false;
				enemys.add(args[4]);
				
			}
			else if(args[3].equalsIgnoreCase("del")){
				if(args.length!=5)
					return false;
				enemys.remove(Integer.parseInt(args[4]));
			}
			else if(args[3].equalsIgnoreCase("list")){
				String str="";
				for(int i=0;i<enemys.size();i++){
					if(i!=0)
						str+=",";
					
					str+=i+":"+enemys.get(i);
				}
				
				p.sendMessage("敌人列表:"+str);
				return true;
				
				
				
			}else return false;
			
		
			p.sendMessage("§c[怪物生成器]§f操作成功.");
			
			save();
			try {
				Main.saveYml();
			} catch (IOException e) {
				p.sendMessage("§c[怪物生成器]§f保存失败.");
			}
			return true;

		}else
			{
			
			boolean b= cmdElse(args, p);
			save();
			try {
				Main.saveYml();
			} catch (IOException e) {
				p.sendMessage("§c[怪物生成器]§f保存失败.");
			}
			
			return b;
		
			}
		
		return false;

}





/**
 * 技能实例独有的命令
 * 
 */
protected abstract boolean cmdElse(String[] args,Player p) ;



/**
 * 执行技能实例
 * 
 */
protected abstract void run(Mob mob,Entity entity);


/**
 * 返回技能实例属性
 * 
 */
public String see(){
	String str="技能详细信息:\n";
	str+="  名字:"+getsName()+"\n";
	str+="  几率:"+getChance()+"\n";
	str+="  触发类型:"+getTrigger()+"\n";
	str+="  触发范围:"+getRange()+"\n";
	str+="  冷却时间:"+getCooling()+"\n";
	String s="";
	for(int i =0;i<getEnemys().size();i++)
	{
		s+="    "+getEnemys().get(i)+"\n";
		
	}
	if(s.equalsIgnoreCase(""))
		s="无\n";
	
	str+="  触发对象列表:"+s;
	return str+seeNext();
	
}

public abstract String seeNext();




/**
 * 返回技能类型列表
 * 
 */
public static String getList() {
	// TODO 自动生成的方法存根
	return getAllList();
}

private static String getAllList() {
	String s="";
	for(int i=0;i<getSkills().size();i++)
	{
				if(i!=0)
			s+=",";
			
			
			s+=getSkills().get(i).getsName();
				
				

		
	}
	s="所有类型技能列表:"+s;
	return s;
}
public static String getList(String str) {
	if(isType(str)==null)
		return "没有这个技能类型";
	
	
	String s="";
	for(int i=0;i<getSkills().size();i++)
	{
		if(getSkills().get(i).getType().equalsIgnoreCase(str));
		{		if(i!=0)
			s+=",";
			
			
			s+=getSkills().get(i).getsName();
				
				

		}
	}
	s=str+"类型技能列表:"+s;
	return s;
}




/**
 * 返回向下技能类型帮助文本
 * 
 */
public static String help(String str) {
	Class skill = isType(str);
if(skill==null)
	return "该技能类型不存在.";
	try {
		String s = (String) skill.getMethod("help").invoke(null);
		return help()+"\n"+s;
	} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException
			| SecurityException e) {
		e.printStackTrace();
		return "";
	}

	
	
}

public static String help() {
	return "  /mobs skill modify chance [触发几率] 设置该技能被触发的几率.\n"+
				"  /mobs skill modify cooling [冷却时间(tick)] 使用一次后的冷却时间(20tick约等于1秒)\n"+
				"  /mobs skill modify trigger [触发方式] 触发技能的方式,可选:\n"+
				"    TRIGGER_CYCLE 周期\n"+
				"    TRIGGER_ATTACK 攻击\n"+
				"    TRIGGER_HURT 受到伤害\n"+
				"    TRIGGER_DYING 濒死\n"+
				"    TRIGGER_TARGET 瞄准\n"+
				"    TRIGGER_BETARGET 被瞄准\n"+
				"  /mobs skill modify range [触发范围] 触发后技能指向的对象,可选:\n"+
				"    RANGE_WORLD 所在世界\n"+
				"    RANGE_CHUNK 所在区块\n"+
				"    RANGE_TARGET 触发者(该类型触发方式不可为周期.)\n"+
				"  /mobs skill modify enemys [add/del/list] 可触发实体列表,例子:"+
				"    /mobs skill modify enemys add PLAYER (该技能可以被所有玩家触发)"+
				"    /mobs skill modify enemys add LIVINGENTITY (该技能可以被所有生物触发)"+
				"    /mobs skill modify enemys add ZOMBIE (该技能可以被所有僵尸触发)";

	
}

public static Class isType(String str){
	for(int i=0;i<types.size();i++){
		try {
			if(((String)types.get(i).getMethod("getType").invoke(types.get(i))).equalsIgnoreCase(str)){
				return types.get(i);
				
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			
			return null;
		}
	}
	
	return null;
}


public static String getTypes() {
	String str="";
	for(int i=0;i<types.size();i++){
		try {
			str+=types.get(i).getMethod("getType").invoke(types.get(i));
			
			if(i!=types.size()-1)
				str+=",";
			
			
		} catch (Exception e) {

		} 
	}
	
	str="技能类型列表:"+str;
	return str;
}
public static String getType() {
	return "Skill";
}
public static Skill getSkill(String skill) {
	
	for(int i=0;i<skills.size();i++){
		if(skills.get(i).getsName().equalsIgnoreCase(skill))
			return skills.get(i);
	}
	return null;
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
		this.sName=sName;
			trigger=TRIGGER_ATTACK;
			chance=25;
			range=RANGE_TARGET;
			cooling=500;
			 newSkillNext();
		addSkills(this);
		save();
	}

public Skill(ConfigurationSection cfg) {
	this.cfg=cfg;
	trigger=cfg.getString("trigger");
	chance=cfg.getInt("chance");
	range=cfg.getString("range");
	cooling=cfg.getInt("cooling");
	enemys=(ArrayList<String>) cfg.getList("enemys");
	sName=cfg.getName();
	skillNext(cfg);
	addSkills(this);
}

protected  abstract void skillNext(ConfigurationSection cfg);
/**
 * 新建时进行的操作/super()
 */

protected abstract void newSkillNext();
/**
 * 将属性对其到cfg文件/super()
 */
public void save(){
	saveNext();
	cfg.set("trigger",trigger);
	cfg.set("chance",chance);
	cfg.set("range",range);
	cfg.set("enemys",enemys);
	cfg.set("cooling",cooling);
	try {
		cfg.set("type", (String)this.getClass().getMethod("getType").invoke(null));
	} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException
			| SecurityException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
}


	
	protected abstract void saveNext() ;
	public boolean isEnemy(String en) {
		if(EntityType.fromName(en)==null&!en.equalsIgnoreCase("PLAYER"))
			return false;
			
			
			
			for(int i=0;i<enemys.size();i++){
				if(enemys.get(i).equalsIgnoreCase(en))
				return true;
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
	public void runSkill(Mob mob,List<Entity> e) {
		if(chance>(Math.random()*100)){
			long time=System.currentTimeMillis();
			if(time-BECOOLING>cooling*50)
			{BECOOLING=System.currentTimeMillis();
				
				for(int i =0;i<e.size();i++)
			{
				if(isEnemy(e.get(i).getType().name()))
				this.run(mob,e.get(i));
				}
				
				
			
			}

		
		}
		
	}
	public void runSkill(Mob mob, Entity e) {
		ArrayList<Entity> a = new ArrayList<Entity>();
		a.add(e);
	runSkill(mob,e);
		
	}
	
	

	public static int isSkill(String string) {

		for(int i=0;i<skills.size();i++){
			if(skills.get(i).getsName().equalsIgnoreCase(string)){
				return i;
			}
		}
		return -1;
	}
	public static Skill newSkill(ConfigurationSection config) {

		
		Class t = null;
		String tt=config.getString("type");
		try {
			for(int i=0;i<types.size();i++){
			if(((String)types.get(i).getMethod("getType").invoke(types.get(i))).equalsIgnoreCase(tt)){
				t=types.get(i);
			}
			}
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	if(t==null)
		return null;
	
	try {
		return (Skill) t.getDeclaredConstructor(ConfigurationSection.class).newInstance(config);
	} catch (Exception e) {

		e.printStackTrace();
		return null;

	}
		
		
		
	}



}
