package cn.rpgmc.run;

import java.io.IOException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.mob.TitleShows;
import cn.rpgmc.utils.Send;

public class Manager extends BukkitRunnable {

	private long startTime = 0;

	public static final int RUNS = 10;

	public Manager() {

		startTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		long g = 0;

		g++;
		if (g % 10 == 0)
			g = 0;

		if (g == 0)
			low();

		long g1 = 0;
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

		
		


	}




}
