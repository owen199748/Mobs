package cn.rpgmc.bean.mob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.run.Main;

public class Mob {
	private LivingEntity e = null;
	private boolean isAttrCover = true;
	private int dmg = 0;
	private int exp = 0;
	private HashMap<Skill, Long> skills = new HashMap<Skill, Long>();
	private ArrayList<ItemStack> drop;
	private static final ArrayList<Mob> mobs = new ArrayList<Mob>();
	private BossName bossName = new BossName();

	public boolean isAttrCover() {
		return isAttrCover;
	}

	public void setAttrCover(boolean isAttrCover) {
		this.isAttrCover = isAttrCover;
	}

	public ArrayList<Mob> getMobs() {
		return mobs;

	}

	public void runSkill(String target, Entity t) {
		if (skills == null)
			return;

		for (int i = 0; i < skills.keySet().toArray().length; i++) {
			Skill sk = (Skill) skills.keySet().toArray()[i];
			if (sk.getTrigger().equalsIgnoreCase(target)) {
				if (target.equalsIgnoreCase(Skill.TRIGGER_CYCLE))
					t = null;

				sk.runSkill(this, t);
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
				return mobs.get(i);
			}
		}
		return null;
	}

	public ArrayList<Skill> getSkills() {
		ArrayList<Skill> a = new ArrayList<Skill>(skills.keySet());
		return a;
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

	public Mob(int dmg, LivingEntity e, ArrayList<ItemStack> drop,
			ArrayList<Skill> skills, int exp, boolean isAttrCover,
			BossName bossName) {
		HashMap<Skill, Long> h = new HashMap<Skill, Long>();
		for (int i = 0; i < skills.size(); i++)
			h.put(skills.get(i), new Long(0));
		this.skills = h;
		this.dmg = dmg;
		this.e = e;
		this.drop = drop;
		this.exp = exp;
		this.isAttrCover = isAttrCover;
		this.bossName = bossName;
		mobs.add(this);
	}

	public void showName(Player p) {
		if (!this.bossName.isEnable())
			return;

		new TitleShows(p, this, bossName.getValue(), bossName.getNearby())
				.runTaskAsynchronously(Main.getMain());
	}


	public static void killAll() {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).getE().remove();
		}
		mobs.clear();
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
				return getMob(es.get(i).getEntityId());

		return null;
	}

	public static void checkAll() {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(mobs.size() - 1 - i).getE().isDead())
				mobs.get(mobs.size() - 1 - i).remove();

		}

	}

}
