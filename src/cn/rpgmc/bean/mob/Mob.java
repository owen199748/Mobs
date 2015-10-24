package cn.rpgmc.bean.mob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import cn.rpgmc.bean.skill.Skill;

public class Mob {
	private LivingEntity e = null;
	private boolean isAttrCover = true;
	private int dmg = 0;
	private int exp = 0;
	private HashMap<Skill, Long> skills = new HashMap<Skill, Long>();
	private ArrayList<ItemStack> drop;
	private static final ArrayList<Mob> mobs = new ArrayList<Mob>();

	public boolean isAttrCover() {
		return isAttrCover;
	}

	public void setAttrCover(boolean isAttrCover) {
		this.isAttrCover = isAttrCover;
	}

	public ArrayList<Mob> getMobs() {
		return mobs;

	}

	public void runSkill(String target, Entity t, Entity[] c, List<Entity> w) {
		if (skills == null)
			return;

		for (int i = 0; i < skills.keySet().toArray().length; i++) {
			Skill sk = (Skill) skills.keySet().toArray()[i];
			if (sk.getTrigger().equalsIgnoreCase(target)) {
				if (target.equalsIgnoreCase(Skill.TRIGGER_CYCLE))
					t = null;

				sk.runSkill(this, t, Arrays.asList(c), w);
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
			ArrayList<Skill> skills, int exp, boolean isAttrCover) {
		HashMap<Skill, Long> h = new HashMap<Skill, Long>();
		for (int i = 0; i < skills.size(); i++)
			h.put(skills.get(i), new Long(0));
		this.skills = h;
		this.dmg = dmg;
		this.e = e;
		this.drop = drop;
		this.exp = exp;
		this.isAttrCover = isAttrCover;
		mobs.add(this);
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
		mobs.remove(this);

	}

}
