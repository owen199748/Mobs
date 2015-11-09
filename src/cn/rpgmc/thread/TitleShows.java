package cn.rpgmc.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.utils.TitleAPI;

public class TitleShows extends BukkitRunnable {
	private static Map<Player, Integer> players = new HashMap<Player, Integer>();
	private Player p;
	private Mob m;
	private String value = "\u2764";
	private String[] values = { "§4", "§c", "§8" };
	private int nearby = 10;
	private String show;

	public TitleShows(Player p, Mob m, String value, int nearby) {
		this.p = p;
		this.m = m;
		this.nearby = nearby;
		this.show = value.replaceAll("%name%", m.getE().getCustomName()).replaceAll("%maxhp%", Double.toString(((LivingEntity)m.getE()).getMaxHealth()));
		players.put(p, m.getE().getEntityId());
	}

	public static Map<Player, Integer> getPlayers() {
		return players;
	}
	@Override
	public void run() {

		boolean b = true;
		while (b) {
			TitleAPI.sendActionBar(p, getShow(10));
			List<Entity> es = p.getNearbyEntities(nearby, nearby, nearby);
			boolean bb = false;
			for (int i = 0; i < es.size(); i++)
				if (es.get(i).getEntityId() == m.getE().getEntityId())
					bb = true;

			b = bb;

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		TitleAPI.sendTitle(p, 0, Integer.MAX_VALUE, 0, null, null);
		players.put(p, null);
	}

	private String getShow(int l) {
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

		return show.replaceAll("%bar%", str).replaceAll("%hp%", Double.toString(e.getHealth()));

	}

}
