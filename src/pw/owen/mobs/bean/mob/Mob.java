package pw.owen.mobs.bean.mob;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.inventory.ItemStack;

import pw.owen.mobs.bean.skill.Skill;
import pw.owen.mobs.bean.spawn.PointSpawn;
import pw.owen.mobs.bean.spawn.Spawn;
import pw.owen.mobs.bean.spawn.WorldSpawn;
import pw.owen.mobs.run.Main;
import pw.owen.mobs.thread.TitleShows;
import pw.owen.mobs.utils.Send;
import pw.owen.mobs.utils.StringEncrypt;
import pw.owen.mobs.utils.mobtype.MobType;


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
	private Boolean autoSave = true;
	private HashSet<String> target;

public HashSet<String> getTarget() {
	return target;
}
	public boolean isNoNatureDamage() {
		return noNatureDamage;
	}

	public Boolean isNoRepel() {
		return noRepel;
	}
	public boolean isAttrCover() {
		return isAttrCover;
	}

	public boolean isAutoSave() {
		return autoSave;
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
			Boolean noRepel, MobType type, Boolean noNatureDamage,
			Boolean autoSave, HashSet<String> target) {
		this(spawnOf, e.getUniqueId().toString(), dmg, e, drop,
				skills, exp, isAttrCover, bossName, sName, rider, noRepel,
				type, noNatureDamage, autoSave,target);
		
	}

	public Mob(Object spawnOf, String id, int dmg, LivingEntity e,
			ArrayList<ItemStack> drop, ArrayList<Skill> skills, int exp,
			boolean isAttrCover, BossName bossName, String sName, String rider,
			Boolean noRepel, MobType type, boolean noNatureDamage,
			Boolean autoSave, HashSet<String> target) {
		this.type = type;
		if(target!=null)
		this.target=target;
		else
			this.target=new HashSet<String>();
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
		this.autoSave = autoSave;
		this.e = e;
		this.drop = drop;
		this.exp = exp;
		this.isAttrCover = isAttrCover;
		this.bossName = bossName;
		this.noNatureDamage = noNatureDamage;
		this.id = id;
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


		List<Entity> rm = new ArrayList<Entity>();
		for (int l = 0; l < Bukkit.getWorlds().size(); l++)
			for (int i = 0; i < Bukkit.getWorlds().get(l).getEntities().size(); i++) {
				Entity es = Bukkit.getWorlds().get(l).getEntities().get(i);
				if (getMob(es) != null)
					rm.add(es);
			}
		
		for (int i = 0; i < rm.size(); i++)
			rm.get(i).remove();

		ArrayList<Mob> ms = new ArrayList<Mob>(mobs);
		for (int i = 0; i < ms.size(); i++)
			ms.get(i).remove();
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
		MobModel.remove(this);
		for (int i = 0; i < Spawn.getSpawns().size(); i++)
			Spawn.getSpawns().get(i).kill(this);

	}

	public void onlyRemove() {
		mobs.remove(this);
		MobModel.remove(this);
		for (int i = 0; i < Spawn.getSpawns().size(); i++)
			Spawn.getSpawns().get(i).onlyKill(this);

	}

	public static List<Mob> getMob(List<Entity> l) {
		ArrayList<Mob> s = new ArrayList<Mob>();
		for (int i = 0; i < l.size(); i++)
			if (getMob(getId(l.get(i))) != null)
				s.add(getMob(getId(l.get(i))));
		if (s.size() == 0)
			return null;
		else
			return s;
	}

	public static Mob getMob(Entity e) {

		for (int i = 0; i < mobs.size(); i++)
			if (mobs.get(i).getId().equals(Mob.getId(e)))
				return mobs.get(i);

		return null;
	}

	public static boolean isMob(List<Entity> l) {
		for (int i = 0; i < l.size(); i++)
			if (getMob(getId(l.get(i))) != null)
				return true;

		return false;
	}

	public static String getId(Entity e) {
		if (e == null)
			return null;
		return e.getUniqueId().toString();
	}

	public static Mob getNearbyBoss(Player p, double x, double y, double z) {
		List<Entity> es = p.getNearbyEntities(x, y, z);
		for (int i = 0; i < es.size(); i++)
 {
			Mob mob = getMob(getId(es.get(i)));
			if (mob != null)
				if (mob.getBossName().isEnable())
					return mob;
		}
		return null;
	}


	public static void checkAll() {
		ArrayList<Mob> ms = new ArrayList<Mob>(mobs);
		for (int i = 0; i < ms.size(); i++)
			if (ms.get(i).getE().isDead())
				ms.get(i).remove();

	}

	public MobType getType() {
		return type;
	}

	public static void saveAll() throws IOException {
		Main.getMobYml().set("Mobs", null);
		Main.getMobYml().createSection("Mobs");
		ConfigurationSection section = Main.getMobYml()
				.getConfigurationSection("Mobs");
		for (int i = 0; i < mobs.size(); i++)
			if (mobs.get(i) != null) {
				if (!(mobs.get(i)).getE().isDead()) {
					if ((mobs.get(i)).isAutoSave()) {
						MobSave save = new MobSave(mobs.get(i));
						String key = StringEncrypt.getBase64(save.toJson()
								.replaceAll("\n", ""));

						ConfigurationSection s1 = section.createSection(key);
						s1.set("Drop", save.ADrop());
						s1.set("Eqpt", Arrays.asList(save.AEQPT()));
					}
				}
			}
		GZIPOutputStream gz = null;
		try {
			gz = new GZIPOutputStream(new FileOutputStream(
					Main.getMobSaveFile()));
			gz.write(Main.getMobYml().saveToString().getBytes());
			gz.finish();
			gz.flush();
			gz.close();
		} finally {
			gz.close();
		}


	}

	public static Mob getMob(String id) {
		if (id == null)
			return null;
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

	public static boolean changeEntity(Entity e) {
		String eid = getId(e);
		if (eid == null)
			return false;
		for(int i=0;i<mobs.size();i++)
			if (mobs.get(i).getId().equals(eid))
				if (e instanceof LivingEntity)
					if (e != mobs.get(i).getE())
 {

					mobs.get(i).getE().remove();
					mobs.get(i).setE((LivingEntity) e);
					return true;
				}
		return false;
	}

	public boolean getNewEntity() {
		for (int i = 0; i < Bukkit.getWorlds().size(); i++) {
			List<Entity> ls = Bukkit.getWorlds().get(i).getEntities();
			for (int r = 0; r < ls.size(); r++)
				if (ls.get(r).getUniqueId().toString().equals(getId())) {
					Entity ne = ls.get(r);
					if (ne instanceof LivingEntity)
						if (ne != e) {
						e.remove();
						setE((LivingEntity) ne);
						return true;
						} else
							return true;

				}
		}
		return false;
	}

	public static Entity getEntity(String uuid) {
		// Send.sendConsole("get" + Bukkit.getWorlds().size());
		for (int i = 0; i < Bukkit.getWorlds().size(); i++) {
			List<Entity> ls = Bukkit.getWorlds().get(i).getEntities();
			for (int r = 0; r < ls.size(); i++)
				if (ls.get(i).getUniqueId().toString().equals(uuid))
					return ls.get(i);
				else
					Send.sendConsole(ls.get(i).getUniqueId().toString() + "|"
							+ uuid);
			// Send.sendConsole(ls.size() + "");
		}
		return null;

	}
	public boolean isTarget(TargetReason reason) {
TargetSelect tg = TargetSelect.valuesOfReason(reason);

			if(tg!=null)
				for(int i=0;i<target.size();i++)
					if(target.toArray()[i].equals(tg.name()))
						return true;
			return false;
	}



}
