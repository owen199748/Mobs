package cn.rpgmc.bean.spawn;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import cn.rpgmc.bean.mob.Mob;

public class PointSpawn extends Spawn {
	private static ArrayList<PointSpawn> Pmobcreates = new ArrayList<PointSpawn>();
	private Loc p;
	private int range = 15;
	private int one = 1;
	private boolean onPoint = false;
	private boolean onMove = true;

	public static ArrayList<PointSpawn> getPmobcreates() {
		return Pmobcreates;
	}



	public Location getP() {
		return p.getLocation();
	}

	public int getRange() {
		return range;
	}


	public void setP(Location p) {
		this.p = new Loc(p);
	}

	public void setRange(int range) {
		this.range = range;
	}

	public PointSpawn(String sName, ConfigurationSection cfg, Location p) {
		super(sName, cfg);
		range = 15;
		this.p = new Loc(p);
		this.one = 1;
		this.onPoint = false;
		this.onMove = false;
		Pmobcreates.add(this);
	}

	public PointSpawn(ConfigurationSection cfg) {
		super(cfg);
		range = cfg.getInt("range");
		p = new Loc(cfg.getConfigurationSection("point").getString("WORLD"),
				cfg.getConfigurationSection("point").getInt("X"), cfg
						.getConfigurationSection("point").getInt("Y"), cfg
						.getConfigurationSection("point").getInt("Z"));
		one = cfg.getInt("one");
		onPoint = cfg.getBoolean("onPoint");
		onMove = cfg.getBoolean("onMove");
		Pmobcreates.add(this);
	}

	@Override
	public void save() {
		getCfg().set("one", one);
		getCfg().createSection("center");
		getCfg().createSection("point");
		getCfg().getConfigurationSection("point").set("X", p.getX());
		getCfg().getConfigurationSection("point").set("Y", p.getY());
		getCfg().getConfigurationSection("point").set("Z", p.getZ());
		getCfg().getConfigurationSection("point").set("WORLD", p.getWorld());
		getCfg().set("range", range);
		getCfg().set("onPoint", onPoint);
		getCfg().set("onMove", onMove);
		super.save();
	}

	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}

	@Override
	public String getCreateType() {
		// TODO 自动生成的方法存根
		return this.POINTMOBCREATE;
	}

	public void test() {
		if (!isOnMove())
			return;

		ArrayList<Mob> m = getMobs();
		for (int i = 0; i < m.size(); i++) {
			if (Math.abs(m.get(i).getE().getLocation().getBlockX()
					- getP().getBlockX()) > getRange()) {
				m.get(i).getE().teleport(getP());
			}

			if (Math.abs(m.get(i).getE().getLocation().getBlockY()
					- getP().getBlockY()) > getRange()) {
				m.get(i).getE().teleport(getP());
			}
		}
	}

	@Override
	public String getSee() {

		String s1 = "刷新点位置:" + p.getX() + "," + p.getY() + "," + p.getZ() + "|"
				+ p.getWorld();
		String s2 = "只在点上刷新:" + onPoint;
		String s3 = "只在刷新范围内活动:" + onMove;
		String s4 = "怪物刷新范围:" + range;
		String s5 = "一次刷新数量:" + one;
		return getMainSee() + "\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4
				+ "\n" + s5;
	}

	public static int isPSpawn(String string) {
		for (int i = 0; i < Pmobcreates.size(); i++) {
			if (Pmobcreates.get(i).getcName().equalsIgnoreCase(string)) {
				return i;
			}
		}
		return -1;

	}

	public void setOnPoint(boolean onPoint) {
		this.onPoint = onPoint;
	}

	public boolean isOnPoint() {
		return onPoint;
	}

	public void setOnMove(boolean onMove) {
		this.onMove = onMove;
	}

	public boolean isOnMove() {
		return onMove;
	}

	public Location getRandomLocation() {
		if (onPoint)
			return p.getLocation();

		return getRandomLocation(p, range);

	}

	private Location getRandomLocation(Loc p, int range) {
		int x = p.getX() + ((int) (Math.random() * range * 2) - range);
		int z = p.getZ() + ((int) (Math.random() * range * 2) - range);
		World w = Bukkit.getWorld(p.getWorld());
		if (w == null)
			return null;

		int y = canSpawn(x, z, p.getY(), w, p);

		if (y != -1)
			return new Location(w, x, y, z);
		else
			return getRandomLocation(p, range);


	}


	private int canSpawn(int x, int z, int startY, World w, Loc p) {
		int max = w.getMaxHeight();

		List<Integer> is = new ArrayList<Integer>();
		boolean up = true;
		boolean down = true;
		for (int i = 0 ; i <= max; i++) {
			if (!up && !down)
				continue;
			int r = (i+1) % 2;
			if (r == 1) {
				if (i + startY > max)
					up = false;
				if (up)
					if (!w.getBlockAt(x, i + startY, z).getType().name()
							.equalsIgnoreCase(Material.AIR.name()))
						if (w.getBlockAt(x, i + startY + 1, z).getType().name()
								.equalsIgnoreCase(Material.AIR.name()))
							if (w.getBlockAt(x, i + startY + 2, z).getType()
									.name()
									.equalsIgnoreCase(Material.AIR.name()))
								is.add(i + startY + 2);
							else if (w.getBlockAt(x, startY - i, z).getType()
									.name()
									.equalsIgnoreCase(Material.AIR.name()))
								if (w.getBlockAt(x, startY - i - 1, z)
										.getType().name()
										.equalsIgnoreCase(Material.AIR.name()))
									if (!w.getBlockAt(x, startY - i - 2, z)
											.getType()
											.name()
											.equalsIgnoreCase(
													Material.AIR.name()))
										is.add(startY - i);

			} else {
				if (startY - i < 0)
					down = false;

				if (!down)
					if (!w.getBlockAt(x, i + startY, z).getType().name()
							.equalsIgnoreCase(Material.AIR.name()))
						if (w.getBlockAt(x, i + startY + 1, z).getType().name()
								.equalsIgnoreCase(Material.AIR.name()))
							if (w.getBlockAt(x, i + startY + 2, z).getType()
									.name()
									.equalsIgnoreCase(Material.AIR.name()))
								is.add(i + startY + 2);
							else if (w.getBlockAt(x, startY - i, z).getType()
									.name()
									.equalsIgnoreCase(Material.AIR.name()))
								if (w.getBlockAt(x, startY - i - 1, z)
										.getType().name()
										.equalsIgnoreCase(Material.AIR.name()))
									if (!w.getBlockAt(x, startY - i - 2, z)
											.getType()
											.name()
											.equalsIgnoreCase(
													Material.AIR.name()))
										is.add(startY - i);

			}
			

		}

		if(is.size()==0)
			return -1;
		
		  int min = is.get(0);
	        for(int i=1;i<is.size();i++){
	            if(Math.abs(is.get(i)-startY)<Math.abs(min-startY)){
	                int temp = 0;
	                temp =min;
	                min = is.get(i);
	                is.set(i, temp);
	            }
		
	}

		return min - 1;
	}

	public static PointSpawn getPointSpawn(String spawner) {
		for (int i = 0; i < Pmobcreates.size(); i++)
			if (Pmobcreates.get(i).getcName().equals(spawner))
				return Pmobcreates.get(i);


		return null;
	}

	public static void addPointMob(Mob mob) {

	}

}
