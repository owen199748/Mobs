package cn.rpgmc.mobs.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cn.rpgmc.mobs.bean.mob.Mob;
import cn.rpgmc.mobs.utils.Send;

public class Manager extends BukkitRunnable {

	private long startTime = 0;
	private long g = 0;
	private long g1 = 0;
	public static final int RUNS = 10;

	public Manager() {

		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {

		low();

		g1++;
		if (g1 % (60 * 2) == 0)
			g1 = 0;

		if (g1 == 0)
			low1();

	}

	private void low1() {
		Mob.checkAll();
		try {
			Mob.saveAll();
		} catch (IOException e) {
			Send.sendConsole(Send.COLOR.RED + "π÷ŒÔ∂ØÃ¨±£¥Ê ß∞‹.");
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
			if(!m.getBossName().isEnable())
				continue;
			
			
			
			
			
			int nb = m.getBossName().getNearby();
			List<Entity> pss = m.getE().getNearbyEntities(nb,nb,nb);
			List<Player>ps= new ArrayList<Player>();
			for(int l=0;l<pss.size();l++)
				if(pss.get(l).getType()==EntityType.PLAYER)
					ps.add((Player) pss.get(l));
			
			
			for (int l = 0; l < ps.size(); l++)
 {
				// if (TitleShows.getPlayers().get(ps.get(l)) == null)
				TitleShows.show(ps.get(l), m);
			}
			
			



		}


		
		


	}




}
