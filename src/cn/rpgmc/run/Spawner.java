package cn.rpgmc.run;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import cn.rpgmc.bean.mob.DropItemStack;
import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.bean.spawn.PointSpawn;
import cn.rpgmc.bean.spawn.Spawn;
import cn.rpgmc.bean.spawn.WorldSpawn;
import cn.rpgmc.bean.utils.HP;

public class Spawner extends BukkitRunnable {
private long g=0;
private long startTime=0;
public static final int RUNS=10; 

	public Spawner() {
		
		startTime=System.currentTimeMillis();
	}

	public void run() {
		
	g++; 
	if(g%10==0)
		g=0;


	Spawn spawn = Spawn.getNextSpawn();
	if(spawn==null)
		return;
	
	if(g==0)
		lowSpawn(spawn);



		

	long n  = System.currentTimeMillis()-startTime;
	if(spawn.getMobModel()==null)
		return;
	
int a = spawn.getAll()-spawn.getMobs().size();//计算刷新几个
int rr=0;
if(spawn.getCreateType().equalsIgnoreCase(Spawn.POINTMOBCREATE)){
	rr=((PointSpawn)spawn).getOne();
}else if(spawn.getCreateType().equalsIgnoreCase(Spawn.WORLDMOBCREATE)){
	rr=1;
	a=1;
}

if(a<=0)
	return;
else if(a>rr)
	a=rr;

	ArrayList<Location> locs=new ArrayList<Location>();

	if(spawn.getCreateType().equalsIgnoreCase(Spawn.POINTMOBCREATE))
		
	{
		PointSpawn pmob=(PointSpawn) spawn;
		String[] ps=new String[]{
				(int)(pmob.getP().getX())+""
				,(int)(pmob.getP().getY())+""
				,(int)(pmob.getP().getZ())+""
				,pmob.getP().getWorld().getName()};
		if(ps.length!=4)
		{return;}
		for(int l=0;l<a;l++){
		 locs.add( new Location(Bukkit.getServer().getWorld(ps[3]),Integer.parseInt(ps[0])
				,Integer.parseInt(ps[1]),
				Integer.parseInt(ps[2])));
		}
		
	}else if(spawn.getCreateType().equalsIgnoreCase(Spawn.WORLDMOBCREATE))
	{
		
		
		for(int l=0;l<a;l++){
locs.addAll(((WorldSpawn)spawn).getLoc());
			}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	if(spawn.getNow()<(long)(n/(spawn.getTime()*50))){
	//达到这个进行一次刷怪
		
		

	

		
			
		if(spawn.getMobModel()!=null)
			for(int x=0;x<locs.size();x++){
			ArrayList<DropItemStack> item =spawn.getMobModel().getDrop();
			spawn.spawnMob(locs.get(x));
			}
			

	
	}
	
	spawn.setNow((long)(n/(spawn.getTime()*50)));
		
	
	
	


	}


	private void lowSpawn(Spawn spawn) {
			Spawn mob=spawn;
		ArrayList<Mob> mobs = mob.getMobs();
		for(int l=0;l<mobs.size();l++)
		{Mob m = mobs.get(mobs.size()-(l+1));
			
			if(m.getE().isDead()){
				mobs.remove(mobs.size()-(l+1));
				
			}else {
				if(mob instanceof PointSpawn){
				PointSpawn pmob = (PointSpawn) mob;

				pmob.test();
			}
			if(m.getSkills()!=null){
				ArrayList<Skill> skill = m.getSkills();
				for(int ii=0;ii<skill.size();ii++){
					Skill	s=skill.get(ii);
					if(s.getTrigger().equalsIgnoreCase(Skill.TRIGGER_CYCLE))
					{
						if(s.getRange().equalsIgnoreCase(Skill.RANGE_WORLD))
						{
							m.runSkill(s,m.getE().getWorld().getEntities());
						}else if(s.getRange().equalsIgnoreCase(Skill.RANGE_CHUNK)){
							m.runSkill(s,Arrays.asList(m.getE().getLocation().getChunk().getEntities()));
						}
						
					}
								}
			}
				
			}
			

		}
	
		
	}














	


}
