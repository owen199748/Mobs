package cn.rpgmc.mobs.bean.mob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import cn.rpgmc.mobs.bean.skill.Skill;
import cn.rpgmc.mobs.bean.spawn.PointSpawn;
import cn.rpgmc.mobs.bean.spawn.Spawn;
import cn.rpgmc.mobs.bean.spawn.WorldSpawn;
import cn.rpgmc.mobs.run.Main;
import cn.rpgmc.mobs.thread.TitleShows;
import cn.rpgmc.mobs.utils.StringEncrypt;
import cn.rpgmc.mobs.utils.mobtype.MobType;


public class Mob {
	private LivingEntity e = null;
	private boolean isAttrCover = true;
	private int dmg = 0;
	private int exp = 0;
	private HashMap<Skill, Long> skills = new HashMap<Skill, Long>();
	private ArrayList<ItemStack> drop;
	private static final ArrayList<Mob> mobs = new ArrayList<Mob>();
	private BossName bossName = new BossName();
	private String sName;
	private String id;
	private String rider = null;
	private Object spawner = null;
	private Boolean noRepel = false;
	private MobType type;
	private boolean noNatureDamage = false;


	public boolean isNoNatureDamage() {
		return noNatureDamage;
	}

	public Boolean isNoRepel() {
		return noRepel;
	}
	public boolean isAttrCover() {
		return isAttrCover;
	}

	public BossName getBossName() {
		return bossName;

	}

	public void setBossName(BossName bossName) {
		this.bossName = bossName;
	}

	public void setSkills(HashMap<Skill, Long> skills) {
		this.skills = skills;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}
	public void setAttrCover(boolean isAttrCover) {
		this.isAttrCover = isAttrCover;
	}

	public static ArrayList<Mob> getMobs() {
		return mobs;

	}

	public void runSkillAuto(Skill skill) {
		skill.runSkill(this, new ArrayList<Entity>(), null);
	}
	public void runSkill(String target, Entity t, Event event) {
		if (skills == null)
			return;

		for (int i = 0; i < skills.keySet().toArray().length; i++) {
			Skill sk = (Skill) skills.keySet().toArray()[i];
			if (sk.getTrigger().equalsIgnoreCase(target)) {
				if (target.equalsIgnoreCase(Skill.TRIGGER_CYCLE))
					t = null;

				sk.runSkill(this, t, event);
			}
		}

	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExp() {
		return exp;
	}

	public static boolean isMob(int entityId) {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).getE().getEntityId() == entityId) {
				return true;
			}
		}
		return false;
	}

	public static Mob getMob(int entityId) {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).getE().getEntityId() == entityId) {

				if (MobModel.getMobModel(mobs.get(i).getsName()) != null)
 {
					MobModel.getMobModel(mobs.get(i).getsName()).addMob(
							mobs.get(i));
					return mobs.get(i);
				}

			}
		}
		return null;
	}

	public ArrayList<Skill> getSkills() {
		ArrayList<Skill> a = new ArrayList<Skill>(skills.keySet());
		return a;
	}

	public HashMap<Skill, Long> getSkills1() {

		return skills;
	}
	public int getDmg() {
		return dmg;
	}

	public ArrayList<ItemStack> getDrop() {
		return drop;
	}

	public Entity getE() {
		return e;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public void setDrop(ArrayList<ItemStack> drop) {
		this.drop = drop;
	}

	public void setE(LivingEntity e) {
		this.e = e;
	}

	public String getsName() {
		return sName;
	}

	public Mob(Object spawnOf, int dmg, LivingEntity e,
			ArrayList<ItemStack> drop,
			ArrayList<Skill> skills, int exp, boolean isAttrCover,
 BossName bossName, String sName, String rider,
			Boolean noRepel, MobType type, Boolean noNatureDamage) {
		this(spawnOf, StringEncrypt
				.getBase64(Math.random() + "/" + System.currentTimeMillis()
						+ "/" + e.getLocation().toString()), dmg, e, drop,
				skills, exp, isAttrCover, bossName, sName, rider, noRepel,
				type, noNatureDamage);

	}

	public Mob(Object spawnOf, String id, int dmg, LivingEntity e,
			ArrayList<ItemStack> drop, ArrayList<Skill> skills, int exp,
			boolean isAttrCover, BossName bossName, String sName, String rider,
			Boolean noRepel, MobType type, boolean noNatureDamage) {
		this.type = type;
		HashMap<Skill, Long> h = new HashMap<Skill, Long>();
		for (int i = 0; i < skills.size(); i++)
			h.put(skills.get(i), new Long(0));
		this.spawner = spawnOf;
		if (spawner instanceof Spawn)
 {
			String cn = ((Spawn) spawner).getcName();
			Spawn sp = null;
			if (spawner instanceof PointSpawn)
				sp = PointSpawn.getPointSpawn(cn);
		else if (spawner instanceof WorldSpawn)
				sp = WorldSpawn.getWorldSpawn(cn);

			if (sp != null) {
				sp.addMob(this);
				sp.addElseMob(Mob.getMob(rider));
			}
		}
		this.rider = rider;
		this.sName = sName;
		this.skills = h;
		this.dmg = dmg;
		this.e = e;
		this.drop = drop;
		this.exp = exp;
		this.isAttrCover = isAttrCover;
		this.bossName = bossName;
		this.noNatureDamage = noNatureDamage;
		this.id = id;
		e.setMetadata("Mobs", new FixedMetadataValue(Main.getMain(), id));
		this.noRepel = noRepel;
		mobs.add(this);

	}

	public String getId() {
		return id;
	}

	public Object getSpawner() {
		return spawner;
	}


	public void showName(Player p) {
		TitleShows.show(p, this);
	}


	public static void killAll() {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).getE().remove();
		}
		mobs.clear();
		List<Entity> rm = new ArrayList<Entity>();
		for (int l = 0; l < Bukkit.getWorlds().size(); l++)
			for (int i = 0; i < Bukkit.getWorlds().get(l).getEntities().size(); i++) {
				Entity es = Bukkit.getWorlds().get(l).getEntities().get(i);
				if (es.getMetadata("Mobs") != null
						&& es.getMetadata("Mobs").size() != 0)
					rm.add(es);
			}
		
		for (int i = 0; i < rm.size(); i++)
			rm.get(i).remove();
			
			}



	public long getBeCooling(Skill skill) {
		if (skills.get(skill) != null)
			return skills.get(skill);

		return 0;
	}

	public void setBeCooling(Skill skill, long l) {
		skills.put(skill, l);

	}

	public void remove() {
		getE().remove();
		mobs.remove(this);

	}

	public static List<Mob> getMob(List<Entity> l) {
		ArrayList<Mob> s = new ArrayList<Mob>();
		for (int i = 0; i < l.size(); i++)
			if (isMob(l.get(i).getEntityId()))
				s.add(getMob(l.get(i).getEntityId()));
		if (s.size() == 0)
			return null;
		else
			return s;
	}

	public static boolean isMob(List<Entity> l) {
		for (int i = 0; i < l.size(); i++)
			if (isMob(l.get(i).getEntityId()))
				return true;

		return false;
	}

	public static Mob getNearbyBoss(Player p, double x, double y, double z) {
		List<Entity> es = p.getNearbyEntities(x, y, z);
		for (int i = 0; i < es.size(); i++)
			if (isMob(es.get(i).getEntityId()))
				if (getMob(es.get(i).getEntityId()).getBossName().isEnable())
				return getMob(es.get(i).getEntityId());

		return null;
	}


	public static void checkAll() {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(mobs.size() - 1 - i).getE().isDead())
				mobs.get(mobs.size() - 1 - i).remove();

		}

	}

	public MobType getType() {
		return type;
	}
	public static void saveAll() throws IOException {
		Main.getMobYml().set("Mobs", null);
		Main.getMobYml().createSection("Mobs");
		ConfigurationSection section = Main.getMobYml()
				.getConfigurationSection("Mobs");
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i) == null)
				continue;
			if (mobs.get(i).getE().isDead())
				continue;

			MobSave save = new MobSave(mobs.get(i));
			String key = StringEncrypt.getBase64(save.toJson().replaceAll("\n",
					""));

			ConfigurationSection s1 = section.createSection(key);
			s1.set("Drop", save.ADrop());
			s1.set("Eqpt", Arrays.asList(save.AEQPT()));

		}

		

		Main.getMobYml().save(Main.getMobSaveFile());


	}

	public static Mob getMob(String id) {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).getId().equals(id))
				return mobs.get(i);
		}

		return null;
	}

	public String getRider() {
		return rider;
	}
	public static void checkRider() {

		for (int i = 0; i < mobs.size(); i++)
			if (mobs.get(i).getRider() != null)
				mobs.get(i).ride();

	}

	private boolean ride() {
		
		if(id.equals(rider))
			return false;

		for(int i=0;i<mobs.size();i++)
			if(mobs.get(i).getId().equals(rider))
 {
				this.getE().setPassenger(mobs.get(i).getE());
				return true;
			}
		return false;

					}




}
