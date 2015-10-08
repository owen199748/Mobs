package cn.rpgmc.bean.skill;

import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Skill_Message extends Skill{
private String msg="";
	public Skill_Message(ConfigurationSection cfg) {
		super(cfg);
		msg=cfg.getString("msg");
	
	}
	Skill_Message(String sName,ConfigurationSection cfg){
		super(sName,cfg);
		setType("Message");
	}

	@Override
	protected void run(Entity e) {
		if(e  instanceof Player) {
		((Player)e).sendMessage(msg.replaceAll("&", "¡ì"));
		}
		
	}
	@Override
		public void save() {
		getCfg().set("msg", msg);
			super.save();
		}


}
