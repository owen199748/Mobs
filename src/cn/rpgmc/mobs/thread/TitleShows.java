package cn.rpgmc.mobs.thread;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cn.rpgmc.mobs.bean.mob.Mob;
import cn.rpgmc.mobs.utils.TitleAPI;

public class TitleShows extends BukkitRunnable {
	public static final long RUNS = 10;
	private static Map<Player, Mob> players = new HashMap<Player, Mob>();
	private String value = "\u2764";
	private String[] values = { "§4", "§c", "§8" };

	public static Map<Player, Mob> getPlayers() {
		return players;
	}

	@Override
	public void run() {

		Object[] key = players.keySet().toArray();
		for (int i = 0; i < key.length; i++) {
			Player p = (Player) key[i];
			Mob m = players.get(p);
			if (m == null) {
				setNull(p);
				continue;
			}
			if (m.getE().isDead())
 {

				players.put(p, null);
				setNull(p);
				continue;
			}


			if (Math.abs(m.getE().getLocation()
					.distanceSquared(p.getLocation())) > m
					.getBossName()
							.getNearby())
 {
						players.put(p, null);
						setNull(p);
						continue;
					}


						TitleAPI.sendActionBar(p, getShow(10, m));

		}



	}

	private void setNull(Player p) {
		TitleAPI.sendActionBar(p, "");
		// TODO 自动生成的方法存根

	}

	private String getShow(int l, Mob m) {
		String v = m
				.getBossName()
				.getValue()
				.replaceAll("%name%", m.getE().getCustomName())
				.replaceAll(
						"%maxhp%",
						Double.toString(((LivingEntity) m.getE())
								.getMaxHealth()));
		LivingEntity e=(LivingEntity) m.getE();
		int r = (int) (e.getHealth() / (e.getMaxHealth() / (l * values.length)));
		int r1 = (int) (r / values.length);
		int r2 = r - ((int) (r / values.length) * values.length);
		int r3 = l - r1;
		if (r2 > 0)
			r3--;
		String str = "";
		str += values[0];
		for (int i = 0; i < r1; i++) {
			str += value;
		}

		if (r2 > 0) {
			if (r2 < values.length - 1)
				str += values[r2] + value;
			else {
				str += values[0] + value;
			}
		}
		str += values[values.length - 1];
		for (int i = 0; i < r3; i++) {
			str += value;
		}

		return v.replaceAll("%bar%", str).replaceAll("%hp%",
				Double.toString(e.getHealth()));

	}

	public static void show(Player p, Mob m) {
		players.put(p, m);

	}

}
