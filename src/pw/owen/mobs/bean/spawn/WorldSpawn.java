package pw.owen.mobs.bean.spawn;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import pw.owen.mobs.bean.mob.Mob;

public class WorldSpawn extends Spawn {
	private static ArrayList<WorldSpawn> Wmobcreates = new ArrayList<WorldSpawn>();
	private ArrayList<World> world = new ArrayList<World>();
	private double chance = 0;
	private int playerNearby = 0;
	private int maxInChunk = -1;
	private int maxInWorld = -1;
	private int maxInServer = -1;
	private boolean onPlayerNum = true;

	// TODO:增加公式计算属性增幅

	public static ArrayList<WorldSpawn> getWmobcreates() {
		return Wmobcreates;
	}

	public int getPlayerNearby() {
		return playerNearby;
	}

	public void setPlayerNearby(int playerNearby) {
		this.playerNearby = playerNearby;
	}

	public int getMaxInChunk() {
		return maxInChunk;
	}

	public int getMaxInServer() {
		return maxInServer;
	}

	public int getMaxInWorld() {
		return maxInWorld;
	}

	public void setOnPlayerNum(boolean onPlayerNum) {
		this.onPlayerNum = onPlayerNum;
	}

	public boolean isOnPlayerNum() {
		return onPlayerNum;
	}
	public void setMaxInChunk(int maxInChunk) {
		this.maxInChunk = maxInChunk;
	}

	public void setMaxInServer(int maxInServer) {
		this.maxInServer = maxInServer;
	}

	public void setMaxInWorld(int maxInWorld) {
		this.maxInWorld = maxInWorld;
	}
	public static int isWSpawn(String string) {
		for (int i = 0; i < Wmobcreates.size(); i++) {
			if (Wmobcreates.get(i).getcName().equalsIgnoreCase(string)) {
				return i;
			}
		}
		return -1;

	}

	public WorldSpawn(ConfigurationSection cfg) {
		super(cfg);
		ArrayList<String> worlds = (ArrayList<String>) cfg.getList("world");
		for (int i = 0; i < worlds.size(); i++) {
			if (Bukkit.getWorld(worlds.get(i)) != null)
			world.add(Bukkit.getWorld(worlds.get(i)));
		}
		chance = cfg.getDouble("chance");
		playerNearby = cfg.getInt("playerNearby");
		Wmobcreates.add(this);
		maxInChunk = cfg.getInt("maxInChunk");
		maxInWorld = cfg.getInt("maxInWorld");
		maxInServer = cfg.getInt("maxInServer");
		onPlayerNum = cfg.getBoolean("onPlayerNum");
	}

	public WorldSpawn(String sName, ConfigurationSection cfg,
			ArrayList<World> world) {
		super(sName, cfg);
		this.world = world;
		chance = 5;
		playerNearby = 16;
		Wmobcreates.add(this);
		maxInChunk = -1;
		maxInWorld = -1;
		maxInServer = -1;
		onPlayerNum = true;
	}

	@Override
	public void save() {
		getCfg().set("chance", chance);
		ArrayList<String> worlds = new ArrayList<String>();
		for (int i = 0; i < world.size(); i++) {
			worlds.add(world.get(i).getName());
		}
		getCfg().set("world", worlds);
		getCfg().set("PlayerNearby", playerNearby);
		getCfg().set("maxInChunk", maxInChunk);
		getCfg().set("maxInWorld", maxInWorld);
		getCfg().set("maxInServer", maxInServer);
		getCfg().set("onPlayerNum", onPlayerNum);
		super.save();
	}

	@Override
	public String getCreateType() {
		// TODO 自动生成的方法存根
		return this.WORLDMOBCREATE;
	}

	public ArrayList<World> getWorld() {
		return world;
	}

	public boolean is() {

		if (chance > (int) (Math.random() * 100))
			return true;

		return false;

	}

	public ArrayList<Location> getLoc() {
		if(onPlayerNum)
		return getLocOnPlayerNum();
		else return getLocOnOne();
	}
	
	
	
	private ArrayList<Location> getLocOnOne() {
		ArrayList<Location> loc = new ArrayList<Location>();
		for (int s = 0; s < getWorld().size(); s++) {
			World w = getWorld().get(s);

			if (w != null)
				if (w.getPlayers().size() != 0)
				if (is())

 {

					Player p = w.getPlayers().get(
							(int) (w.getPlayers().size() * Math.random()));

					int x = p.getLocation().getBlockX()
							+ (((int) (Math.random() * (getPlayerNearby() * 2))) - getPlayerNearby());
					int z = p.getLocation().getBlockZ()
							+ (((int) (Math.random() * (getPlayerNearby() * 2))) - getPlayerNearby());
					int y1 = -1;
					int y2 = -1;
					int y = -1;
					for (int i = p.getLocation().getBlockY(); i <= w
							.getMaxHeight(); i++) {
						if (w.getBlockAt(x, i, z).getType().name()
								.equalsIgnoreCase(Material.AIR.name()))
							;
						{
							if (!w.getBlockAt(x, i - 1, z).getType().name()
									.equalsIgnoreCase(Material.AIR.name())) {
								y1 = i;
								break;
							}
						}
					}
					for (int i = p.getLocation().getBlockY(); i >= 0; i--) {
						if (w.getBlockAt(x, i, z).getType().name()
								.equalsIgnoreCase(Material.AIR.name()))
							;
						{
							if (!w.getBlockAt(x, i - 1, z).getType().name()
									.equalsIgnoreCase(Material.AIR.name())) {
								y2 = i + 1;
								break;
							}
						}
					}

					if (Math.abs(y1 - p.getLocation().getBlockY()) > Math
							.abs(y2 - p.getLocation().getBlockY())) {
						y = y2;
					} else {
						y = y1;
					}

					if (y == -1)
						continue;

					loc.add(new Location(w, x, y, z));


				}

		}
		return loc;
	}

	public ArrayList<Location> getLocOnPlayerNum() {
		ArrayList<Location> loc = new ArrayList<Location>();
		for (int s = 0; s < getWorld().size(); s++) {
			World w = getWorld().get(s);
			if (w != null)
			for (int l = 0; l < w.getPlayers().size(); l++) {
				Player p = w.getPlayers().get(l);
				if (is()) {
					int x = p.getLocation().getBlockX()
							+ (((int) (Math.random() * (getPlayerNearby() * 2))) - getPlayerNearby());
					int z = p.getLocation().getBlockZ()
							+ (((int) (Math.random() * (getPlayerNearby() * 2))) - getPlayerNearby());
					int y1 = -1;
					int y2 = -1;
					int y = -1;
					for (int i = p.getLocation().getBlockY(); i <= w
							.getMaxHeight(); i++) {
						if (w.getBlockAt(x, i, z).getType().name()
								.equalsIgnoreCase(Material.AIR.name()))
							;
						{
							if (!w.getBlockAt(x, i - 1, z).getType().name()
									.equalsIgnoreCase(Material.AIR.name())) {
								y1 = i;
								break;
							}
						}
					}
					for (int i = p.getLocation().getBlockY(); i >= 0; i--) {
						if (w.getBlockAt(x, i, z).getType().name()
								.equalsIgnoreCase(Material.AIR.name()))
							;
						{
							if (!w.getBlockAt(x, i - 1, z).getType().name()
									.equalsIgnoreCase(Material.AIR.name())) {
								y2 = i + 1;
								break;
							}
						}
					}

					if (Math.abs(y1 - p.getLocation().getBlockY()) > Math
							.abs(y2 - p.getLocation().getBlockY())) {
						y = y2;
					} else {
						y = y1;
					}

					if (y == -1)
						continue;

					loc.add(new Location(w, x, y, z));

				}
			}

		}
		return loc;

	}

	public void setChance(double chance) {
		this.chance = chance;
	}

	public double getChance() {
		return chance;
	}

	@Override
	public Mob spawnMob(Location loc) {
		if (getMobModel() == null)
			return null;
		if (getMaxInServer() != -1)
		if (getMobs().size() >= getMaxInServer())
			return null;

		List<Entity> es2 = loc.getWorld().getEntities();

		int l = 0;
		if (getMaxInWorld() != -1) {
		for (int i = 0; i < es2.size(); i++)
				if (isSpawnMob(Mob.getId(es2.get(i))))
				l++;

		if (l >= getMaxInWorld())
			return null;
		}

		if (getMaxInChunk() != -1) {
		Entity[] es = loc.getChunk().getEntities();
		l = 0;
		for (int i = 0; i < es.length; i++)
				if (isSpawnMob(Mob.getId(es[i])))
				l++;

		if (l >= getMaxInChunk())
			return null;
		}

			return super.spawnMob(loc);
	}

	@Override
	public String getSee() {

		String s1 = "刷新几率:" + chance;
		String s2 = "离玩家最远距离:" + playerNearby;
		String s3 = "区块内最多数量:" + maxInChunk;
		String s4 = "世界内最多数量:" + maxInWorld;
		String s5 = "服务器内最多数量:" + maxInServer;
		String s6 = "刷新几率是否考虑玩家数:" + onPlayerNum;
		String s7 = "  刷新世界:";
		for (int i = 0; i < world.size(); i++) {
			s6 += ("\n    " + world.get(i).getName());
		}

		return getMainSee() + "\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4
				+ "\n" + s5 + "\n" + s6 + "\n" + s7;
	}

	public static WorldSpawn getWorldSpawn(String spawner) {
		for (int i = 0; i < Wmobcreates.size(); i++)
			if (Wmobcreates.get(i).getcName().equals(spawner))
				return Wmobcreates.get(i);

		return null;
	}

	@Override
	public int getSurplusQuantity() {
		return 1;
	}

}
