package cn.rpgmc.mobs.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cn.rpgmc.mobs.bean.mob.Mob;
import cn.rpgmc.mobs.bean.skill.Skill;
import cn.rpgmc.mobs.utils.Send;

public class Manager extends BukkitRunnable {

	private long startTime = 0;
	private long g = 0;
	private long g1 = 0;
	private long g2 = 0;
	public static final int RUNS = 10;

	public Manager() {

		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {

		low();

		Skill.skillRunAll();

		g1++;
		if (g1 % (60 * 2) == 0)
			g1 = 0;

		if (g1 == 0)
			low1();

		low2();

	}

	private void low2() {
		ArrayList<Mob> ms = Mob.getMobs();
		for (int i = 0; i < ms.size(); i++) {
			Mob m = ms.get(i);
			if (m == null)
				continue;
			if (m.getE().isDead())
				continue;

			m.runSkill(Skill.TRIGGER_CYCLE, null, null);
		}

	}

	private void low1() {
		Mob.checkAll();
		try {
			Mob.saveAll();
		} catch (IOException e) {
			Send.sendConsole(Send.COLOR.RED + "¹ÖÎï¶¯Ì¬±£´æÊ§°Ü.");
		}

	}

	private void low() {
		ArrayList<Mob> ms = Mob.getMobs();
		for (int i = 0; i < ms.size(); i++) {
			Mob m = ms.get(i);
			if (m == null)
				continue;
			if (m.getE().isDead())
				continue;

			// m.runSkill(Skill.TRIGGER_CYCLE, null, null);

			if(!m.getBossName().isEnable())
				continue;
			
			
			
			
			
			int nb = m.getBossName().getNearby();
			List<Player> ps = new ArrayList<Player>();
			if(nb==-1)
				ps = new ArrayList<Player>(Bukkit.getOnlinePlayers());
			else if(nb==0)
				ps = m.getE().getWorld().getPlayers();
			else
			{List<Entity> pss =m.getE().getNearbyEntities(nb,nb,nb);
			for(int l=0;l<pss.size();l++)
				if(pss.get(l).getType()==EntityType.PLAYER)
					ps.add((Player) pss.get(l));
			}
			
			for (int l = 0; l < ps.size(); l++)
 {
				// if (TitleShows.getPlayers().get(ps.get(l)) == null)
				TitleShows.show(ps.get(l), m);
			}
			
			



		}


		
		


	}




}
