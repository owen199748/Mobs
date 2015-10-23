package cn.rpgmc.bean.spawn;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.mob.MobModel;
import cn.rpgmc.run.Main;
import cn.rpgmc.run.Spawner;

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
	private int all = 0;
	private static int THREADS;
	private static int THREADS_INT = -1;
	private long last = 0;
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	static {
		THREADS = Runtime.getRuntime().availableProcessors() * 2;
		MAIN_CFG = Main.getCfg();
		MAIN_F = Main.getF();
	}

	protected void setLast() {
		last = System.currentTimeMillis();
	}

	public long getLast() {
		return last;
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

	private Spawn() {

	}

	public static void removeEntity(LivingEntity entity) {
		if (entity == null)
			return;
		for (int i = 0; i < getSpawns().size(); i++) {
			Spawn spawn = getSpawns().get(i);
			for (int l = 0; l < spawn.getMobs().size(); l++) {
				if (spawn.getMobs().get(l).getE().getEntityId() == entity
						.getEntityId()) {
					spawn.getMobs().remove(l);
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

		Mob m = getMobModel().spawnMob(loc);
		if (m == null)
			return null;
		mobs.add(m);
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
		all = 6;

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
		all = cfg.getInt("all");
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
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).getE().remove();

		}
		mobs.clear();

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
		cfg.set("all", all);

	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public ConfigurationSection getCfg() {
		return cfg;
	}

	public void remove() {
		getSpawns().remove(this);
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
		String s5 = "最大数量:" + all;
		return s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5;
	}
}
