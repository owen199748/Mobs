package cn.rpgmc.bean.mob;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import cn.rpgmc.bean.integer.Damage;
import cn.rpgmc.bean.integer.EXP;
import cn.rpgmc.bean.integer.HP;
import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.run.Main;

public class MobModel {
	private static FileConfiguration MAIN_CFG;
	private static File MAIN_F;
	private static ArrayList<MobModel> mobModel = new ArrayList<MobModel>();
	private String sName;
	private String displayName = null;
	private HP hp = new HP(0);
	private EXP exp = new EXP(0);
	private Eqpt eqpt = new Eqpt();
	private Damage dmg=new Damage(0);
	private EntityType type=null;
	private boolean isAttrCover = true;
	private ArrayList<DropItemStack> drop=new ArrayList<DropItemStack>();
	private int dropType = 0;
	private SurvivalLimit survivalLimit =new SurvivalLimit();
	private ConfigurationSection cfg = null;
	private ArrayList<Skill> skills=new ArrayList<Skill>();
	private ArrayList<Mob> mobs=new ArrayList<Mob>();
	static { 
		MAIN_CFG=Main.getCfg();
		MAIN_F=Main.getF();
	}
	
public SurvivalLimit getSurvivalLimit() {
	return survivalLimit;
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

public boolean isMob(int r){
	for(int i =0;i<mobs.size();i++)
	{
		if(mobs.get(i).getE().getEntityId()==r){
			return true;
		}
	}
	return false;
}

	private ArrayList<Skill> getSkills() {
		// TODO �Զ����ɵķ������
		return skills;
	}
	public Eqpt getEqpt() {
		return eqpt;
	}
public static ArrayList<MobModel> getMobModel() {
	return mobModel;
}
public static int isMobModel(String sName) {
	for(int i=0;i<mobModel.size();i++){
		if(mobModel.get(i).getsName().equalsIgnoreCase(sName)){
			return i;
		}
	}
	return -1;
}
public MobModel(String sName,ConfigurationSection cfg) throws IOException {
	this.sName=sName;
	displayName = "&2ZOMBIE";
	hp.setMax(40);
	hp.setMin(20);
	dmg.setMin(3);
	dmg.setMax(7);
	exp.setMin(5);
	exp.setMax(10);
	type=EntityType.ZOMBIE;
	dropType =2;
	cfg.createSection(sName);
	this.cfg=cfg.getConfigurationSection(sName);


	
for(int i=0;i<mobModel.size();i++){
	if(mobModel.get(i).getsName().equalsIgnoreCase(this.sName))
	{
		mobModel.set(i,this);
		return;
	}
}
mobModel.add(this);
this.save();
	
}
	public MobModel(ConfigurationSection cfg) {
		this.cfg=cfg;
		sName=cfg.getName();
	displayName = (String)cfg.get("displayName");
	hp.setMax(cfg.getInt("hp_max"));
	hp.setMin( cfg.getInt("hp_min"));
	dmg.setMin(cfg.getInt("dmg_min"));
	dmg.setMax(cfg.getInt("dmg_max"));
	exp.setMin(cfg.getInt("exp_min"));
	exp.setMax(cfg.getInt("exp_max"));
	type=EntityType.fromName( (String)cfg.get("type"));
	dropType = cfg.getInt("dropType");
	isAttrCover=cfg.getBoolean("isAttrCover");
	
	
	
	ConfigurationSection dp = cfg.getConfigurationSection("drop");
	if(dp!=null)
		
	{
		Set<String> ar = dp.getKeys(false);
		for(int i=0;i<ar.size();i++){
			List<ItemStack> lst = (List<ItemStack>) dp.getList((String) ar.toArray()[i]);
			for(int l=0;l<lst.size();l++){
				drop.add(new DropItemStack(lst.get(l),Integer.parseInt((String) ar.toArray()[i])));
			}
		}
	}
	
	
	

	eqpt = new Eqpt(	(ItemStack)(cfg.getConfigurationSection("eqpt").get("Helmet")),
			(ItemStack)(cfg.getConfigurationSection("eqpt").get("Chestplate")),
			(ItemStack)(cfg.getConfigurationSection("eqpt").get("Leggings")),
			(ItemStack)(cfg.getConfigurationSection("eqpt").get("Boots")),
			(ItemStack)(cfg.getConfigurationSection("eqpt").get("Hand")));
	
	
/////////////���ؼ���
	skills=(ArrayList<Skill>) cfg.getList("skill");
	if(skills==null)
		skills=new ArrayList<Skill>();
	survivalLimit.isNight(cfg.getConfigurationSection("SurvivalLimit").getBoolean("isNight"));
	survivalLimit.isRain(cfg.getConfigurationSection("SurvivalLimit").getBoolean("isRain"));
	survivalLimit.isThundering(cfg.getConfigurationSection("SurvivalLimit").getBoolean("isThundering"));

	for(int i=0;i<mobModel.size();i++){
		if(mobModel.get(i).getsName().equalsIgnoreCase(this.sName))
		{
			mobModel.set(i,this);
			return;
		}
	}
	
	mobModel.add(this);
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
	public void addDrop(ItemStack drop,int l) {
		this.drop.add(new DropItemStack(drop,l));
	}
	public boolean delDrop(int drop) {
		if(this.drop.remove(drop)==null){
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
	public void setType(EntityType type) {
		this.type = type;
	}
	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}
	
	public boolean remove() {
		if(!getMobModel().remove(this))
			return false;
		ConfigurationSection m = MAIN_CFG.getConfigurationSection("MobModel");
		if(m==null)
			return true;
		m.set(this.getsName(), null);
		return true;
		}
	
	
	public void save() throws IOException{
cfg.createSection("SurvivalLimit");
cfg.getConfigurationSection("SurvivalLimit").set("isNight", survivalLimit.isNight());
cfg.getConfigurationSection("SurvivalLimit").set("isRain",  survivalLimit.isRain());
cfg.getConfigurationSection("SurvivalLimit").set("isThundering",  survivalLimit.isThundering());
cfg.set("displayName",displayName);
cfg.set("hp_max",hp.getMax());
cfg.set("hp_min"	,hp.getMin());
cfg.set("dmg_min",dmg.getMin());
cfg.set("dmg_max",dmg.getMax());
cfg.set("exp_min",exp.getMin());
cfg.set("exp_max",exp.getMax());
cfg.set("type",type.getName());
cfg.set("dropType",dropType);
cfg.set("isAttrCover", isAttrCover);
cfg.createSection("eqpt");
cfg.getConfigurationSection("eqpt").set("Helmet", eqpt.getHelmet());
cfg.getConfigurationSection("eqpt").set("Chestplate", eqpt.getChestplate());
cfg.getConfigurationSection("eqpt").set("Leggings", eqpt.getLeggings());
cfg.getConfigurationSection("eqpt").set("Boots", eqpt.getBoots());
cfg.getConfigurationSection("eqpt").set("Hand", eqpt.getHand());
cfg.createSection("drop");
for(int i=0;i<drop.size();i++){
	List<ItemStack> l = (List<ItemStack>) cfg.getConfigurationSection("drop").getList(drop.get(i).getI()+"");
	if(l==null){
		l=new ArrayList<ItemStack>();
	}
	l.add(drop.get(i).getItem());
	cfg.getConfigurationSection("drop").set(drop.get(i).getI()+"", l);
}
	
	}
	
	
public Mob spawnMob(Location loc){
if(survivalLimit.isNight()){
	if(loc.getWorld().getTime()<12000){
		return null;
	}
}

if(survivalLimit.isRain()){
	
	
	//if(loc.get.getPlayerWeather().name().equalsIgnoreCase(WeatherType.DOWNFALL.name())){
	//	return null;
	//}
}

if(survivalLimit.isThundering()){
	if(!loc.getWorld().isThundering()){
		return null;
	}
}

	LivingEntity e = (LivingEntity) loc.getWorld().spawnEntity(loc, type);
	
	e.setCustomName(displayName.replaceAll("&","��"));
	double hp = 0;
	if(isAttrCover){
		hp=getHp().getInt();
		hp=getHp().getInt();
		hp=getHp().getInt();
	}else{
		hp=e.getMaxHealth()+getHp().getInt();
		hp=e.getMaxHealth()+getHp().getInt();
		hp=e.getMaxHealth()+getHp().getInt();
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
if(dropType==0)
	isl=null;
else if(dropType==1)
{
	for(int i=0;i<drop.size();i++){
		
			isl.add(drop.get(i).getItem());
		
	}}
else if(dropType==2)
	{for(int i=0;i<drop.size();i++){
		if(drop.get(i).getI()>(int)(Math.random()*100)){
			isl.add(drop.get(i).getItem());
		}
	}
	}

Mob m = new Mob(dmg.getInt(),e,isl,getSkills(),exp.getInt(),isAttrCover);
if(m!=null)
	mobs.add(m);
return m;
	
}

public int getDropType() {
	return dropType;
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
public EntityType getType() {
	return type;
}
public String getMsg() {
	// TODO �Զ����ɵķ������
	return null;
}
public String getDropsMsg() {
	// TODO �Զ����ɵķ������
	return null;
}
public String getSee() {
String s1="������:"+sName;
String s2="��ʾ��:"+displayName;
String s3="Ѫ��:"+hp;
String s4="�˺�:"+dmg;
String s5="  װ��:";
String ss6="";
String ss7="";
String ss8="";
String ss9="";
String ss10="";
if(eqpt.getHelmet()!=null)
ss6=eqpt.getHelmet().getType().name();
else
	ss6="null";

if(eqpt.getChestplate()!=null)
ss7=eqpt.getChestplate().getType().name();
else
	ss7="null";

if(eqpt.getLeggings()!=null)
ss8=eqpt.getLeggings().getType().name();
else
	ss8="null";

if(eqpt.getBoots()!=null)
ss9=eqpt.getBoots().getType().name();
else
	ss9="null";

if(eqpt.getHand()!=null)
ss10=eqpt.getHand().getType().name();
else
	ss10="null";

String s6="    ͷ��:"+ss6;
String s7="    �ؼ�:"+ss7;
String s8="    ����:"+ss8;
String s9="    ѥ��:"+ss9;
String s10="    ����:"+ss10;
String s11="��������:"+type;
String s12="������:"+drop.size()+"��";
String s13="��������:";
if(dropType==0){
	s8+="���޸ĵ���";
}else if(dropType==1){
	s8+="ȫ������";
}else if(dropType==2){
	s8+="�����ʵ���";
}
String s14="  ��������:";
String s15="    �����ˢ��:" +survivalLimit.isDay();
String s16="    ҹ����ˢ��:"+ survivalLimit.isNight();
String s17="    �����ˢ��:" +survivalLimit.isSun();
String s18="    �����ˢ��:" +survivalLimit.isRain();
String s19="    ���ײ�ˢ��:" +survivalLimit.isThundering();
String s20="����:"+skills.size()+"��";
return s1+"\n"+s1+"\n"+s2+"\n"+s3+"\n"+s4+
		"\n"+s5+"\n"+s6+"\n"+s7+"\n"+s8+"\n"+s9+"\n"
		+s10+"\n"+s11+"\n"+s12+"\n"+s13+"\n"+s14+"\n"
		+s15+"\n"+s16+"\n"+s17+"\n"+s18+"\n"+s19+"\n"+s20;



}

}