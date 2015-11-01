package cn.rpgmc.run;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.mob.TitleShows;

public class Manager extends BukkitRunnable {
	private long g = 0;
	private long startTime = 0;
	public static final int RUNS = 10;

	public Manager() {

		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {

		g++;
		if (g % 10 == 0)
			g = 0;

		if (g == 0)
			low();

	}

	private void low() {
		Collection<? extends Player> ps = Bukkit.getServer().getOnlinePlayers();

		for (int i = 0; i < ps.size(); i++) {
			Player p = (Player) ps.toArray()[i];
			Mob m = Mob.getNearbyBoss(p, 10, 10, 10);
			if (m == null)
				continue;
			Integer a = TitleShows.getPlayers().get(p);
			if (a == null || a != m.getE().getEntityId())
			m.showName(p);
		}

		Mob.checkAll();
		
		

	}




}
