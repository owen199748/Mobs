package pw.owen.mobs.thread;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import pw.owen.mobs.bean.mob.Mob;
import pw.owen.mobs.bean.mob.ShowType;
import pw.owen.mobs.utils.TitleAPI;

public class TitleShows implements Runnable {
	public static final long RUNS = 10;
	private static Map<Player, Mob> players = new HashMap<Player, Mob>();
	private String value = "\u2764";
	private String[] values = { "¡ì4", "¡ìc", "¡ì8" };

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
				setNull(p,m);
				continue;
			}
			if (m.getE().isDead())
 {

				players.put(p, null);
				setNull(p,m);
				continue;
			}

			if (m.getBossName().getNearby() == 0
					&& !m.getE().getWorld().getName()
							.equals(p.getWorld().getName())) {
				players.put(p, null);
				setNull(p,m);
				continue;
			}

			if (m.getBossName().getNearby() > 0
					&& Math.abs(m.getE().getLocation()
					.distanceSquared(p.getLocation())) > m
					.getBossName()
							.getNearby())
 {
						players.put(p, null);
						setNull(p,m);
						continue;
					}

show(p, getShow(10, m),m);
						

		}



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
				toDisHp(e.getHealth()));

	}

	private String toDisHp(double hp) {
		int hp1 = (int) hp;
		String low = ".0";
		if (hp - hp1 > 0.75)
			hp1++;
		else if (hp - hp1 > 0.25)
			low = ".5";

		return hp1 + low;
	}

	public static void show(Player p, Mob m) {
		players.put(p, m);

	}
	public static void show(Player p, String show, Mob m) {
if(m.getBossName()!=null){
	ShowType type = m.getBossName().getShowType();
	if(type!=null){
	if(type==ShowType.ACTIONBAR)
		TitleAPI.sendActionBar(p,show);
	else if(type==ShowType.SUBTITLE)
p.sendTitle("", show);
		else if(type==ShowType.TITLE)
			p.sendTitle(show, "");

	}
}
	}

	public static void setNull(Player p, Mob m) {
	show(p, "", m);
	}
	public static void close(Player p,Mob m) {
		players.put(p, null);
		setNull(p,m);
	}

	public static void close(Player p) {
		players.put(p, null);
		setNull(p);
	}

	private static void setNull(Player p) {

		TitleAPI.sendActionBar(p,"");

p.sendTitle("", "");

			p.sendTitle("", "");
	
		
	}


}
