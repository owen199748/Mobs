package cn.rpgmc.run;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.bean.spawn.Spawn;

public class AutoListener implements Listener {
	private static AutoListener ls=null;
	public static AutoListener getLs() {
		return ls;
	}
	AutoListener(){
ls=this;

	}

	@EventHandler
	public void ete(EntityTargetEvent ete)
	{
		if(Mob.isMob(ete.getEntity().getEntityId())){
			Mob m=Mob.getMob(ete.getEntity().getEntityId());
			Entity e = ete.getEntity();
			for(int i=0;i<Mob.getMob(e.getEntityId()).getSkills().size();i++){
				Skill skill = Mob.getMob(e.getEntityId()).getSkills().get(i);
				if(skill.getTrigger().equalsIgnoreCase(Skill.TRIGGER_TARGET))
				if(skill.getRange().equalsIgnoreCase(Skill.RANGE_CHUNK)){
				m.runSkill(skill,Arrays.asList(e.getLocation().getChunk().getEntities()));
				}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_WORLD)){
					m.runSkill(skill,e.getWorld().getEntities());	
				}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_TARGET)){
					ArrayList<Entity> a = new ArrayList<Entity>();
					a.add(ete.getTarget());
					m.runSkill(skill,a);	
				}
				
				
			}
			
		}
		if(ete.getTarget()!=null)
		if(Mob.isMob(ete.getTarget().getEntityId())){
			Mob m=Mob.getMob(ete.getTarget().getEntityId());
			Entity e = ete.getTarget();
			for(int i=0;i<Mob.getMob(e.getEntityId()).getSkills().size();i++){
				Skill skill = Mob.getMob(e.getEntityId()).getSkills().get(i);
				if(skill.getTrigger().equalsIgnoreCase(Skill.TRIGGER_BETARGET))
				if(skill.getRange().equalsIgnoreCase(Skill.RANGE_CHUNK)){
				m.runSkill(skill,Arrays.asList(e.getLocation().getChunk().getEntities()));
				}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_WORLD)){
					m.runSkill(skill,e.getWorld().getEntities());	
				}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_TARGET)){
					ArrayList<Entity> a = new ArrayList<Entity>();
					a.add(ete.getEntity());
					m.runSkill(skill,a);	
				}
				
				
			}
			
		}
	}
	@EventHandler
	public void pje(PlayerJoinEvent pje)
	{
		if(pje.getPlayer().isOp()){
			if(!Main.getCfg().getString("Version").equalsIgnoreCase(Main.getV())){
				
				pje.getPlayer().sendMessage("§c[Mobs]§f插件与存在的配置版本不统一,请删除配置并重载插件.");
				pje.getPlayer().sendMessage("§c[Mobs]§如果确认老版本配置支持当前版本请输入 /Mobs reload 转换为新版本配置.");
			}
			
			
		}

	}

	
	
	
	
	@EventHandler
	public void bpe(PlayerInteractEvent bpe){
		if(bpe.getClickedBlock()==null){
			return;
		}
		if(bpe.getPlayer().isOp())
		if(bpe.getAction().name().equalsIgnoreCase(Action.LEFT_CLICK_BLOCK.name()))


		{
			if(bpe.getItem()!=null)
			if(bpe.getItem().getTypeId()==Main.getClickItem()){

				Main.setO(bpe.getClickedBlock().getLocation());
				bpe.getPlayer().sendMessage("§c[Mobs]§6您已经选定了一个点:"+
						bpe.getClickedBlock().getX()+"x,"+
						bpe.getClickedBlock().getY()+"y,"+
						bpe.getClickedBlock().getZ()+"z,"+"位于世界:"+
						bpe.getClickedBlock().getWorld().getName()
						);
				
				bpe.setCancelled(true);
			}
			
			
			
		}

		
		
	}
	
	
	@EventHandler
	public void edbee(EntityDamageByEntityEvent edbee){
		Mob mob = Mob.getMob(edbee.getDamager().getEntityId());
		if(mob!=null){
int dmg = mob.getDmg();
		if(dmg!=-1){
	if(mob.isAttrCover())
			edbee.setDamage(dmg);
	else
		edbee.setDamage(edbee.getDamage()+dmg);
		}
		}
		
		if(Mob.isMob(edbee.getEntity().getEntityId())){
			Mob m=Mob.getMob(edbee.getEntity().getEntityId());
			Entity e = edbee.getEntity();
			for(int i=0;i<Mob.getMob(e.getEntityId()).getSkills().size();i++){
				Skill skill = Mob.getMob(e.getEntityId()).getSkills().get(i);
				if(skill.getTrigger().equalsIgnoreCase(Skill.TRIGGER_HURT))
				if(skill.getRange().equalsIgnoreCase(Skill.RANGE_CHUNK)){
				m.runSkill(skill,Arrays.asList(e.getLocation().getChunk().getEntities()));
				}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_WORLD)){
					m.runSkill(skill,e.getWorld().getEntities());	
				}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_TARGET)){
					ArrayList<Entity> a = new ArrayList<Entity>();
					a.add(edbee.getDamager());
					m.runSkill(skill,a);	
				}

			}
			
		}
		
		if(Mob.isMob(edbee.getDamager().getEntityId())){
			Mob m=Mob.getMob(edbee.getDamager().getEntityId());
			Entity e = edbee.getDamager();
			for(int i=0;i<Mob.getMob(e.getEntityId()).getSkills().size();i++){
				Skill skill = Mob.getMob(e.getEntityId()).getSkills().get(i);
				if(skill.getTrigger().equalsIgnoreCase(Skill.TRIGGER_ATTACK))
				if(skill.getRange().equalsIgnoreCase(Skill.RANGE_CHUNK)){
				m.runSkill(skill,Arrays.asList(e.getLocation().getChunk().getEntities()));
				}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_WORLD)){
					m.runSkill(skill,e.getWorld().getEntities());	
				}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_TARGET)){
					ArrayList<Entity> a = new ArrayList<Entity>();
					a.add(edbee.getEntity());
					m.runSkill(skill,a);	
				}
				
				
			}
			
		}
	}
	
	
	@EventHandler
	public void ede(EntityDeathEvent ede){
	Spawn.removeEntity(ede.getEntity());
	if(Mob.isMob(ede.getEntity().getEntityId())){
		Entity e = ede.getEntity();
		Mob m = Mob.getMob(e.getEntityId());

		
		if(m.isAttrCover())
		ede.setDroppedExp(m.getExp());
		else
		ede.setDroppedExp(ede.getDroppedExp()+m.getExp());
		for(int i=0;i<m.getSkills().size();i++){
			Skill skill = m.getSkills().get(i);
			if(skill.getTrigger().equalsIgnoreCase(Skill.TRIGGER_DYING))
			if(skill.getRange().equalsIgnoreCase(Skill.RANGE_CHUNK)){
			m.runSkill(skill,Arrays.asList(e.getLocation().getChunk().getEntities()));
			}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_WORLD)){
				m.runSkill(skill,e.getWorld().getEntities());	
			}else if(skill.getRange().equalsIgnoreCase(Skill.RANGE_TARGET)){
				m.runSkill(skill,ede.getEntity().getKiller());	
			}
			
			
		}
		
		
		
		
		if(m.getDrop()!=null){
			ede.getDrops().clear();
			ede.getDrops().addAll(m.getDrop());
		}
		
		
		m.remove();
	}
	
}
}
