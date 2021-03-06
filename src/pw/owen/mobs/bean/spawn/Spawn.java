package pw.owen.mobs.bean.spawn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import pw.owen.mobs.bean.mob.Mob;
import pw.owen.mobs.bean.mob.MobModel;
import pw.owen.mobs.run.Main;
import pw.owen.mobs.thread.Spawner;

public abstract class Spawn {
	public static final String POINTMOBCREATE = "PointSpawn";
	public static final String WORLDMOBCREATE = "WorldSpawn";

	private static FileConfiguration MAIN_CFG = null;
	private static File MAIN_F;
	private static ArrayList<Spawn> spawns = new ArrayList<Spawn>();

	private ConfigurationSection cfg = null;
	private String cName = null;
	private String mm = null;
	private int time = 0;
	private long now = 0;
	private static int THREADS;
	private static int THREADS_INT = -1;
	private long last = 0;
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private ArrayList<Mob> elseMobs = new ArrayList<Mob>();
	static {
		THREADS = Runtime.getRuntime().availableProcessors() * 2;
		MAIN_CFG = Main.getCfg();
		MAIN_F = Main.getF();
	}

	public ArrayList<Mob> getElseMobs() {
		return elseMobs;
	}
	protected void setLast() {
		last = System.currentTimeMillis();
	}

	public long getLast() {
		return last;
	}

	public boolean isSpawnMob(String id) {
		for (int i = 0; i < mobs.size(); i++)
			if (mobs.get(i).getId().equals(id))
				return true;

		return false;
	}

	public static int getTHREADS() {
		return THREADS;
	}

	public static ArrayList<Spawn> getSpawns() {
		return spawns;
	}

	public static void setSpawns(ArrayList<Spawn> spawns) {
		Spawn.spawns = spawns;
	}

	public static void remove(Mob mob) {
		if (mob == null)
			return;
		for (int i = 0; i < getSpawns().size(); i++) {
			Spawn spawn = getSpawns().get(i);
			List<Mob> sl = new ArrayList<Mob>(spawn.getMobs());
			List<Mob> sl2 = new ArrayList<Mob>(spawn.getElseMobs());
			for (int l = 0; l < sl.size(); l++) {
				if (sl.get(l) == null)
					continue;

				if (sl.get(l).getId().equals(mob.getId()))
					sl.get(l).remove();
			}
			for (int l = 0; l < sl2.size(); l++) {
				if (sl2.get(l) == null)
					continue;

				if (sl2.get(l).getId().equals(mob.getId()))
					sl2.get(l).remove();

			}

		}
	}

	public static void remove(String id) {
		if (id == null)
			return;
		for (int i = 0; i < getSpawns().size(); i++) {
			Spawn spawn = getSpawns().get(i);
			List<Mob> sl = new ArrayList<Mob>(spawn.getMobs());
			for (int l = 0; l < sl.size(); l++) {
				if (sl.get(l) == null)
					continue;

				if (sl.get(l).getId().equals(id)) {
					spawn.getMobs().remove(sl.get(l));
				}
			}
		}
	}
	public static Spawn getCreate(String name) {
		for (int i = 0; i < getSpawns().size(); i++) {
			if (getSpawns().get(i).getcName().equalsIgnoreCase(name)) {
				return getSpawns().get(i);
			}
		}
		return null;
	}

	public synchronized static Spawn getNextSpawn() {
		THREADS_INT++;

		if (THREADS_INT >= spawns.size())
			THREADS_INT = 0;

		if (spawns.size() == 0)
			return null;

		Spawn m = getSpawns().get(THREADS_INT);
		long is = System.currentTimeMillis();
		if (m.getLast() < is)
			if (((is - m.getLast()) / (Spawner.RUNS * 50)) >= 1)
				m.setLast();
			else
				return null;

		return m;
	}

	public abstract String getCreateType();

	public Mob spawnMob(Location loc) {
		if (getMobModel() == null)
			return null;

		Mob m = getMobModel().spawnMob(this, loc);
		if (m == null)
			return null;


		return m;

	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public void setMobModel(MobModel mm) {
		this.mm = mm.getsName();
	}

	public void setMobs(ArrayList<Mob> mobs) {
		this.mobs = mobs;
	}

	public String getcName() {
		return cName;
	}

	public MobModel getMobModel() {
		return MobModel.getMobModel(mm);
	}

	public ArrayList<Mob> getMobs() {
		return mobs;
	}

	public Spawn(String sName, ConfigurationSection cfg) {
		cName = sName;
		now = 0;
		mm = null;
		time = 100;


		cfg.createSection(sName);
		this.cfg = cfg.getConfigurationSection(sName);
		for (int i = 0; i < spawns.size(); i++) {
			if (spawns.get(i).getcName().equalsIgnoreCase(this.cName)) {
				spawns.set(i, this);
				return;
			}
		}
		addSpawn(this);
	}

	public Spawn(ConfigurationSection cfg) {

		this.cfg = cfg;
		now = 0;
		cName = cfg.getName();
		if (cfg.getString("MobModel") == null) {
			mm = null;
		} else
			mm = cfg.getString("MobModel");
		time = cfg.getInt("time");

		for (int i = 0; i < spawns.size(); i++) {
			if (spawns.get(i).getcName().equalsIgnoreCase(this.cName)) {
				spawns.set(i, this);
				return;
			}
		}
		addSpawn(this);
	}

	public static void addSpawn(Spawn spawn) {
		for (int i = 0; i < spawns.size(); i++)
			if (spawns.get(i).getcName().equalsIgnoreCase(spawn.getcName())) {
				spawns.set(i, spawn);
				return;
			}

		spawns.add(spawn);
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void killAll() {
		List<Mob> ls = new ArrayList<Mob>();
		ls.addAll(mobs);
		ls.addAll(elseMobs);
		for (int i = 0; i < ls.size(); i++)
			ls.get(i).remove();
		mobs.clear();
		elseMobs.clear();

	}

	public static void killAlls() {
		for (int r = 0; r < spawns.size(); r++) {
			spawns.get(r).killAll();
		}
	}

	public void addItems(ItemStack item, int i) {
		// TODO 自动生成的方法存根

	}

	public static void clear() {
		setSpawns(new ArrayList<Spawn>());

	}

	public void save() {
		if (mm == null)
			cfg.set("MobModel", null);
		else
			cfg.set("MobModel", mm);
		cfg.set("time", time);

	}



	public ConfigurationSection getCfg() {
		return cfg;
	}

	public void remove() {
		getSpawns().remove(this);
		if (this.getCreateType().equalsIgnoreCase(POINTMOBCREATE))
			PointSpawn.getPmobcreates().remove(this);
		else if (this.getCreateType().equalsIgnoreCase(WORLDMOBCREATE))
			WorldSpawn.getWmobcreates().remove(this);

		ConfigurationSection m = MAIN_CFG.getConfigurationSection(this
				.getCreateType());
		if (m == null)
			return;
		m.set(this.getcName(), null);
	}

	abstract public String getSee();

	public String getMainSee() {
		String s1 = "刷新点名:" + cName;
		String s2 = "刷新点类型:" + getCreateType();
		String s3 = "";
		if (mm == null)
			s3 = "怪物模板:" + "无";
		else
			s3 = "怪物模板:" + mm;
		String s4 = "刷新间隔:" + time;
		return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4;
	}

	public void addMob(Mob mob) {
		this.mobs.add(mob);

	}

	public abstract int getSurplusQuantity();
	public void addElseMob(Mob m) {
		elseMobs.add(m);
	}

	public static void killAll(String createType, String c) {
		ArrayList<Mob> ms = Mob.getMobs();
		for (int i = 0; i < ms.size(); i++)
			if (ms.get(i).getSpawner() instanceof Spawn)
				if (((Spawn) ms.get(i).getSpawner()).getCreateType().equals(
						createType))
					if (((Spawn) ms.get(i).getSpawner()).getcName().equals(c))
						ms.get(i).getE().remove();

		Mob.checkAll();


	}

	public void kill(Mob mob) {
		mob.getE().remove();
		mobs.remove(mob);
	}

	public void onlyKill(Mob mob) {
		mobs.remove(mob);
	}

}
