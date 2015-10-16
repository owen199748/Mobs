package cn.rpgmc.bean.skill;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import cn.rpgmc.bean.mob.Mob;

public class Skill_Message extends Skill{
private String msg="";

public Skill_Message(ConfigurationSection cfg) {
	super(cfg);
}

public Skill_Message(String sName2, ConfigurationSection cfg2) {
	super( sName2, cfg2);
	}

	@Override
	protected void skillNext(ConfigurationSection cfg) {
		msg=cfg.getString("msg");
		
	}
	@Override
	protected void newSkillNext() {
		msg="";
	
	}
	@Override
	protected void saveNext() {
		getCfg().set("msg", msg);
		
	}
	@Override
	public String seeNext() {
	
		return "  对话内容:"+msg+"\n";
	}




	@Override
	protected boolean cmdElse(String[] args, Player p) {
		if(args[2].equalsIgnoreCase("msg")){
			if(args.length!=4)
			return false;
		msg=args[3];
		return true;
		
		}
		
		return false;
	}
	@Override
	protected void run(Mob mob,Entity entity) {
		if(entity instanceof Player)
	((Player)entity).sendMessage(msg.replaceAll("%a%", ((LivingEntity)mob.getE()).getCustomName())
			.replaceAll("%b%", ((Player)entity).getDisplayName()).replaceAll("&","§"));

	}

	public static String getType(){
		return "Message";	
	}
	public static String help() {
		
		return "技能类型:对话技能\n"+"技能介绍:触发技能时进行对话.\n"+"指令:\n"+"  /mobs skill modify msg [对话内容] \n(其中对话内容的%a%代表释放技能对象,%b%代表被对话的玩家对象)";
	}


	


}
