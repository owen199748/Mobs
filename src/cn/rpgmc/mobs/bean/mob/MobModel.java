package cn.rpgmc.mobs.bean.mob;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import cn.rpgmc.mobs.bean.skill.Skill;
import cn.rpgmc.mobs.run.Main;
import cn.rpgmc.mobs.utils.mobtype.MobType;
import cn.rpgmc.mobs.utils.mobtype.example.MobType_CanSize;
import cn.rpgmc.mobs.utils.potion.Potion;
import cn.rpgmc.mobs.utils.rangeint.Damage;
import cn.rpgmc.mobs.utils.rangeint.DropNum;
import cn.rpgmc.mobs.utils.rangeint.EXP;
import cn.rpgmc.mobs.utils.rangeint.HP;

public class MobModel {
	private static FileConfiguration MAIN_CFG;
	private static File MAIN_F;
	private static ArrayList<MobModel> mobModel = new ArrayList<MobModel>();
	private String sName;
	private String displayName = null;
	private BossName bossName = new BossName();
	private HP hp = new HP(0);
	private EXP exp = new EXP(0);
	private Eqpt eqpt = new Eqpt();
	private Damage dmg = new Damage(0);
	private MobType type = null;
	private Boolean isAttrCover = true;
	private ArrayList<DropItemStack> drop = new ArrayList<DropItemStack>();
	private Boolean noNatureDamage = false;
	private Boolean autoSave = true;
	private Integer dropType = 0;
	private Boolean noRepel = false;
	private String rider = null;
	private HashMap<String, Integer> potion = new HashMap<String, Integer>();
	private SurvivalLimit survivalLimit = new SurvivalLimit();
	private ConfigurationSection cfg = null;
	private ArrayList<Skill> skills = new ArrayList<Skill>();
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private int size;

	static {
		MAIN_CFG = Main.getCfg();
		MAIN_F = Main.getF();
	}

	public String getSee() {
		String s1 = "怪物名:" + sName;
		String s2 = "显示名:" + displayName;
		String s3 = "血量:" + hp;
		String s4 = "伤害:" + dmg;
		String s5 = "  装备:";
		String ss6 = "";
		String ss7 = "";
		String ss8 = "";
		String ss9 = "";
		String ss10 = "";
		if (eqpt.getHelmet() != null)
			ss6 = eqpt.getHelmet().getType().name();
		else
			ss6 = "null";

		if (eqpt.getChestplate() != null)
			ss7 = eqpt.getChestplate().getType().name();
		else
			ss7 = "null";

		if (eqpt.getLeggings() != null)
			ss8 = eqpt.getLeggings().getType().name();
		else
			ss8 = "null";

		if (eqpt.getBoots() != null)
			ss9 = eqpt.getBoots().getType().name();
		else
			ss9 = "null";

		if (eqpt.getHand() != null)
			ss10 = eqpt.getHand().getType().name();
		else
			ss10 = "null";

		String s6 = "    头盔:" + ss6;
		String s7 = "    胸甲:" + ss7;
		String s8 = "    护腿:" + ss8;
		String s9 = "    靴子:" + ss9;
		String s10 = "    武器:" + ss10;
		String s11 = "怪物类型:" + type;
		String s12 = "掉落物:" + drop.size() + "个";
		String s13 = "掉落类型:";
		if (dropType == 0) {
			s8 += "不修改掉落";
		} else if (dropType == 1) {
			s8 += "全部掉落";
		} else if (dropType == 2) {
			s8 += "按几率掉落";
		}
		String s14 = "  存在限制:";
		String s15 = "    白天才刷新:" + survivalLimit.isDay();
		String s16 = "    夜晚才刷新:" + survivalLimit.isNight();
		String s17 = "    晴天才刷新:" + survivalLimit.isSun();
		String s18 = "    下雨才刷新:" + survivalLimit.isRain();
		String s19 = "    打雷才刷新:" + survivalLimit.isThundering();
		String s20 = "技能:" + skills.size() + "个";
		String s21 = "骑乘者:" + rider;
		String s22 = "抗击退:" + noRepel;
		String s23 = "属性覆盖:" + isAttrCover + "\n" + "取消自然伤害:" + noNatureDamage
				+ "\n" + "关闭服务器是否保存:" + autoSave;
		String s24 = "  Boss名显示方式:";
		String s25 = "    是否开启:" + bossName.isEnable();
		String s26 = "    显示内容:" + bossName.getValue();
		String s27 = "    显示范围:" + bossName.getNearby();
		String s28 = "自带药水属性:" + potion.size() + "种";

		return s1 + "\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4 + "\n" + s5
				+ "\n" + s6 + "\n" + s7 + "\n" + s8 + "\n" + s9 + "\n" + s10
				+ "\n" + s11 + "\n" + s12 + "\n" + s13 + "\n" + s14 + "\n"
				+ s15 + "\n" + s16 + "\n" + s17 + "\n" + s18 + "\n" + s19
				+ "\n" + s20 + "\n" + s21 + "\n" + s22 + "\n" + s23 + "\n"
				+ s24 + "\n" + s25 + "\n" + s26 + "\n" + s27 + "\n" + s28;

	}

	public SurvivalLimit getSurvivalLimit() {
		return survivalLimit;
	}

	public void setRider(String rider) {
		this.rider = rider;
	}

	public Set<String> getPotionList() {
		return potion.keySet();
	}

	public int getPotionLv(String eff) {
		if (potion.get(eff) == null)
			return -1;

		return potion.get(eff);
	}

	public boolean delPotion(String eff) {
		if (potion.get(eff) == null)
			return false;

		potion.remove(eff);
		return true;
	}

	public void addPotion(String eff, int lv) {
		potion.put(eff, lv);
	}

	public MobModel() {
		// TODO 自动生成的构造函数存根
	}

	public void setAttrCover(boolean isAttrCover) {
		this.isAttrCover = isAttrCover;
	}

	public boolean isAttrCover() {
		return isAttrCover;
	}

	public EXP getExp() {
		return exp;
	}

	public void setExp(EXP exp) {
		this.exp = exp;
	}

	public static void setMobModel(ArrayList<MobModel> mobModel) {
		MobModel.mobModel = mobModel;
	}

	public boolean isMob(int r) {
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).getE().getEntityId() == r) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Skill> getSkills() {
		// TODO 自动生成的方法存根
		return skills;
	}

	public boolean delSkills(int i) {
		if (i < 0 | i >= skills.size())
			return false;

		skills.remove(i);
		return true;
	}

	public void addSkill(Skill skill) {
		// TODO 自动生成的方法存根
		skills.add(skill);
	}

	public Eqpt getEqpt() {
		return eqpt;
	}

	public static ArrayList<MobModel> getMobModels() {
		return mobModel;
	}

	public static MobModel getMobModel(String str) {
		for (int i = 0; i < getMobModels().size(); i++) {
			if (getMobModels().get(i).getsName().equalsIgnoreCase(str))
				return getMobModels().get(i);
		}

		return null;
	}

	public void setBossName(BossName bossName) {
		this.bossName = bossName;
	}

	public BossName getBossName() {
		return bossName;
	}
	public static int isMobModel(String sName) {
		if (sName == null)
			return -1;

		for (int i = 0; i < mobModel.size(); i++) {
			if (mobModel.get(i).getsName().equalsIgnoreCase(sName)) {
				return i;
			}
		}
		return -1;
	}

	public MobModel(String sName, ConfigurationSection cfg) throws IOException {
		this.sName = sName;
		displayName = "&2ZOMBIE";
		hp.setMax(40);
		hp.setMin(20);
		dmg.setMin(3);
		dmg.setMax(7);
		exp.setMin(5);
		exp.setMax(10);
		size = 5;
		type = MobType.fromName("僵尸");
		dropType = 2;
		bossName = new BossName();
		cfg.createSection(sName);
		this.cfg = cfg.getConfigurationSection(sName);
		addMobModel(this);
		this.save();

	}

	public MobModel(ConfigurationSection cfg) {
		this.cfg = cfg;
		sName = cfg.getName();
		displayName = (String) cfg.get("displayName");
		ConfigurationSection boss = cfg.getConfigurationSection("bossName");
		if (boss != null) {
			bossName = new BossName(boss.getBoolean("isEnable"),
					boss.getString("value"), boss.getInt("nearby"));
		}

		hp.setMax(cfg.getInt("hp_max"));
		hp.setMin(cfg.getInt("hp_min"));
		dmg.setMin(cfg.getInt("dmg_min"));
		dmg.setMax(cfg.getInt("dmg_max"));
		exp.setMin(cfg.getInt("exp_min"));
		exp.setMax(cfg.getInt("exp_max"));
		size = cfg.getInt("size");
		noNatureDamage = cfg.getBoolean("noNatureDamage");
		type = MobType.fromName((String) cfg.get("type"));
		dropType = cfg.getInt("dropType");
		isAttrCover = cfg.getBoolean("isAttrCover");
		rider = cfg.getString("rider");
		noRepel = cfg.getBoolean("noRepel");
		autoSave = cfg.getBoolean("autoSave");
		ConfigurationSection effs = cfg.getConfigurationSection("potionEffect");
		Set<String> effset = effs.getKeys(false);
		for (int i = 0; i < effset.size(); i++)
			potion.put((String) effset.toArray()[i],
					effs.getInt((String) effset.toArray()[i]));

		ConfigurationSection dp = cfg.getConfigurationSection("drop");
		if (dp != null)

		{
			Set<String> ar = dp.getKeys(false);
			for (int i = 0; i < ar.size(); i++) {
				List<ItemStack> lst = (List<ItemStack>) dp.getList((String) ar
						.toArray()[i]);
				for (int l = 0; l < lst.size(); l++) {
					String[] all = null;
					if (((String) ar.toArray()[i]).indexOf("_") != -1)
						all = ((String) ar.toArray()[i]).split("_");
					else
						all = new String[] { (String) ar.toArray()[i], "-1",
								"-1" };

if(all.length!=3)
	continue;

					int random =Integer.parseInt(all[0]);
					int min=Integer.parseInt(all[1]);
					int max=Integer.parseInt(all[2]);
					if (min == max && min == -1)
						drop.add(new DropItemStack(lst.get(l), random));
					else
						drop.add(new DropItemStack(lst.get(l), random,
								new DropNum(max, min)));
				}
			}
		}

		eqpt = new Eqpt((ItemStack) (cfg.getConfigurationSection("eqpt")
				.get("Helmet")), (ItemStack) (cfg
				.getConfigurationSection("eqpt").get("Chestplate")),
				(ItemStack) (cfg.getConfigurationSection("eqpt")
						.get("Leggings")),
				(ItemStack) (cfg.getConfigurationSection("eqpt").get("Boots")),
				(ItemStack) (cfg.getConfigurationSection("eqpt").get("Hand")));

		// ///////////加载技能
		ArrayList<String> skillStr = (ArrayList<String>) cfg.getList("skills");
		for (int i = 0; i < skillStr.size(); i++) {
			if (Skill.isSkill(skillStr.get(i)) != -1) {
				skills.add(Skill.getSkills()
						.get(Skill.isSkill(skillStr.get(i))));

			}

		}

		survivalLimit.isNight(cfg.getConfigurationSection("SurvivalLimit")
				.getBoolean("isNight"));
		survivalLimit.isRain(cfg.getConfigurationSection("SurvivalLimit")
				.getBoolean("isRain"));
		survivalLimit.isThundering(cfg.getConfigurationSection("SurvivalLimit")
				.getBoolean("isThundering"));

		addMobModel(this);
	}

	public static void addMobModel(MobModel mm) {
		for (int i = 0; i < mobModel.size(); i++)
			if (mobModel.get(i).getsName().equalsIgnoreCase(mm.getsName())) {
				mobModel.set(i, mm);
				return;
			}
		mobModel.add(mm);
	}

	public void setDropType(int dropType) {
		this.dropType = dropType;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setDmg(Damage dmg) {
		this.dmg = dmg;
	}

	public void setDrop(ArrayList<DropItemStack> drop) {
		this.drop = drop;
	}

	public void addDrop(DropItemStack drop) {
		this.drop.add(drop);
	}

	public void addDrop(ItemStack drop, int l) {
		this.drop.add(new DropItemStack(drop, l));
	}

	public boolean delDrop(int drop) {
		if (this.drop.remove(drop) == null) {
			return false;
		}
		return true;
	}

	public void setEqpt(Eqpt eqpt) {
		this.eqpt = eqpt;
	}

	public void setHp(HP hp) {
		this.hp = hp;
	}

	public void setType(MobType type) {
		this.type = type;
	}

	public boolean remove() {
		if (!getMobModels().remove(this))
			return false;
		ConfigurationSection m = MAIN_CFG.getConfigurationSection("MobModel");
		if (m == null)
			return true;
		m.set(this.getsName(), null);
		return true;
	}

	public void setNoRepel(Boolean noRepel) {
		this.noRepel = noRepel;
	}

	public void setNoNatureDamage(Boolean noNatureDamage) {
		this.noNatureDamage = noNatureDamage;
	}

	public void save() throws IOException {
		cfg.set("displayName", displayName);
		cfg.createSection("bossName");
		cfg.getConfigurationSection("bossName").set("isEnable",
				bossName.isEnable());
		cfg.getConfigurationSection("bossName").set("value",
				bossName.getValue());
		cfg.getConfigurationSection("bossName").set("nearby",
				bossName.getNearby());
		cfg.set("hp_max", hp.getMax());
		cfg.set("hp_min", hp.getMin());
		cfg.set("dmg_min", dmg.getMin());
		cfg.set("dmg_max", dmg.getMax());
		cfg.set("exp_min", exp.getMin());
		cfg.set("exp_max", exp.getMax());
		cfg.set("noNatureDamage", noNatureDamage);
		cfg.set("autoSave", autoSave);
		cfg.set("size", size);
		cfg.set("noRepel", noRepel);
		if (type != null)
			cfg.set("type", type.getName());
		else
			cfg.set("type", "Zombie");
		cfg.set("dropType", dropType);
		cfg.set("rider", rider);
		cfg.set("isAttrCover", isAttrCover);
		cfg.createSection("SurvivalLimit");
		cfg.getConfigurationSection("SurvivalLimit").set("isNight",
				survivalLimit.isNight());
		cfg.getConfigurationSection("SurvivalLimit").set("isRain",
				survivalLimit.isRain());
		cfg.getConfigurationSection("SurvivalLimit").set("isThundering",
				survivalLimit.isThundering());
		cfg.createSection("eqpt");
		cfg.getConfigurationSection("eqpt").set("Helmet", eqpt.getHelmet());
		cfg.getConfigurationSection("eqpt").set("Chestplate",
				eqpt.getChestplate());
		cfg.getConfigurationSection("eqpt").set("Leggings", eqpt.getLeggings());
		cfg.getConfigurationSection("eqpt").set("Boots", eqpt.getBoots());
		cfg.getConfigurationSection("eqpt").set("Hand", eqpt.getHand());
		cfg.set("potionEffect", null);
		cfg.createSection("potionEffect");
		Object[] effc = potion.keySet().toArray();
		for (int i = 0; i < effc.length; i++) {
			cfg.getConfigurationSection("potionEffect").set((String) effc[i],
					potion.get(effc[i]));
		}
		cfg.createSection("drop");
		for (int i = 0; i < drop.size(); i++) {
			List<ItemStack> l = (List<ItemStack>) cfg.getConfigurationSection(
					"drop").getList(drop.get(i).getI() + "");
			if (l == null) {
				l = new ArrayList<ItemStack>();
			}
			// EntityType.ARROW
			l.add(drop.get(i).getItem());
			DropNum sz = drop.get(i).getDropSize();
			cfg.getConfigurationSection("drop").set(
					drop.get(i).getI()
							+ "_"
							+ ((sz == null) ? "-1_-1" : sz.getMin() + "_"
									+ sz.getMax()), l);
		}
		ArrayList<String> skillStr = new ArrayList<String>();
		for (int i = 0; i < skills.size(); i++) {
			skillStr.add(skills.get(i).getsName());
		}
		cfg.set("skills", skillStr);

	}

	public String getrider() {
		if (rider == null)
			return "";
		return rider;
	}

	public Mob spawnMob(Object spawnOf, Location loc) {
		if (survivalLimit.isNight()) {
			if (loc.getWorld().getTime() < 12000) {
				return null;
			}
		}

		if (survivalLimit.isRain()) {

			// if(loc.get.getPlayerWeather().name().equalsIgnoreCase(WeatherType.DOWNFALL.name())){
			// return null;
			// }
		}

		if (survivalLimit.isThundering()) {
			if (!loc.getWorld().isThundering()) {
				return null;
			}
		}
		if (loc == null)
			return null;
		if (type == null)
			return null;

		LivingEntity e = null;
		if (type instanceof MobType_CanSize)
			e = MobType.create(type, loc, size);
		else
			e = MobType.create(type, loc);
		Object[] pea = potion.keySet().toArray();
		for (int i = 0; i < pea.length; i++) {
			if (PotionEffectType.getByName(Potion
					.fromCh((String) pea[i])) == null)
				continue;
			e.addPotionEffect(
					new PotionEffect(PotionEffectType
.getByName(Potion
							.fromCh((String) pea[i])), Integer.MAX_VALUE,
							potion.get(pea[i]), true), false);
		}
		String ri = null;
		if (isMobModel(rider) != -1) {

			MobModel riderMob = getMobModels().get(isMobModel(rider));
			Mob riderM = riderMob.spawnMob(e, e.getLocation());
			ri = riderM.getId();
			e.setPassenger(riderM.getE());

		}
		e.setCustomName(displayName.replaceAll("&", "§"));
		double hp = 0;
		if (isAttrCover) {
			hp = getHp().getInt();
			hp = getHp().getInt();
			hp = getHp().getInt();
		} else {
			hp = e.getMaxHealth() + getHp().getInt();
			hp = e.getMaxHealth() + getHp().getInt();
			hp = e.getMaxHealth() + getHp().getInt();
		}

		e.setMaxHealth(hp);
		e.setHealth(hp);
		e.getEquipment().setHelmet(getEqpt().getHelmet());
		e.getEquipment().setChestplate(getEqpt().getChestplate());
		e.getEquipment().setLeggings(getEqpt().getLeggings());
		e.getEquipment().setBoots(getEqpt().getBoots());
		e.getEquipment().setItemInHand(getEqpt().getHand());
		e.getEquipment().setHelmetDropChance(0);
		e.getEquipment().setChestplateDropChance(0);
		e.getEquipment().setLeggingsDropChance(0);
		e.getEquipment().setBootsDropChance(0);
		e.getEquipment().setItemInHandDropChance(0);

		ArrayList<ItemStack> isl = new ArrayList<ItemStack>();
		if (dropType == 0)
			isl = null;
		else if (dropType == 1) {
			for (int i = 0; i < drop.size(); i++) {

				isl.add(drop.get(i).getItem());

			}
		} else if (dropType == 2) {
			for (int i = 0; i < drop.size(); i++) {
				if (drop.get(i).getI() > (int) (Math.random() * 100)) {
					isl.add(drop.get(i).getItem());
				}
			}
		}

		

		Mob m = new Mob(spawnOf, dmg.getInt(), e, isl, getSkills(),
				exp.getInt(),
 isAttrCover, bossName, sName, ri, noRepel, type,
				noNatureDamage, autoSave);
		if (m != null)
 {
			mobs.add(m);
		}

		for (int i = 0; i < m.getSkills().size(); i++)
			if (m.getSkills().get(i).getTrigger()
					.equalsIgnoreCase(Skill.TRIGGER_BESPAWN))
				m.getSkills()
						.get(i)
						.runSkill(
								m,
 m.getE(), null);

		return m;

	}

	public void setAutoSave(Boolean autoSave) {
		this.autoSave = autoSave;
	}

	public int getDropType() {
		return dropType;
	}

	public void addMob(Mob m) {
		mobs.add(m);
	}
	public ConfigurationSection getCfg() {
		return cfg;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ArrayList<DropItemStack> getDrop() {
		return drop;
	}

	public Damage getDmg() {
		return dmg;
	}

	public HP getHp() {
		return hp;
	}

	public static FileConfiguration getMAIN_CFG() {
		return MAIN_CFG;
	}

	public static File getMAIN_F() {
		return MAIN_F;
	}

	public String getsName() {
		return sName;
	}

	public MobType getType() {
		return type;
	}

	public String getMsg() {
		// TODO 自动生成的方法存根
		return null;
	}

	public String getDropsMsg() {
		// TODO 自动生成的方法存根
		return null;
	}



	public void killAll() {
		for (int i = 0; i < Mob.getMobs().size(); i++) {
			if (Mob.getMobs().get(i).getsName().equalsIgnoreCase(this.sName))
				Mob.getMobs().get(i).getE().remove();

		}
		mobs = new ArrayList<Mob>();
		Mob.checkAll();

	}

	public static void del(MobModel a) {
		// TODO 自动生成的方法存根

	}

	public void setType(MobType t, int size) {
		this.size = size;
		setType(t);

	}

	public void addDrop(ItemStack item, int random, DropNum dn) {
		this.drop.add(new DropItemStack(item, random, dn));

	}

}
