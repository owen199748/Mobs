package cn.rpgmc.run;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import cn.rpgmc.bean.mob.DropItemStack;
import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.mob.MobModel;
import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.bean.spawn.PointSpawn;
import cn.rpgmc.bean.spawn.Spawn;
import cn.rpgmc.bean.spawn.WorldSpawn;

public class Main extends JavaPlugin {
	private static int clickItem=0;
	private static MobModel sMobModel=null;
	private static Spawn sSpawn=null;
	private static Skill sSkill=null;
	private static Location o=null;
	private static String V ="";
	private static FileConfiguration cfg;
	private static File f;
	private static AutoListener ls=null;
	private static File file;
	private static ArrayList<Integer> tid=new ArrayList<Integer>();
	private static ArrayList<String> MonsterSpawnBannedWorld=new ArrayList<String>();
	private static ArrayList<String> AnimalSpawnBannedWorld=new ArrayList<String>();
	public static void setsMobModel(MobModel sMobModel) {
		Main.sMobModel = sMobModel;
	}
	public static void setsSpawn(Spawn sSpawn) {
		Main.sSpawn = sSpawn;
	}
	public static Skill getsSkill() {
		return sSkill;
	}
	public static void setsSkill(Skill sSkill) {
		Main.sSkill = sSkill;
	}
	public static Spawn getsSpawn() {
		return sSpawn;
	}
	public static MobModel getsMobModel() {
		return sMobModel;
	}
	public static ArrayList<String> getMonsterSpawnBannedWorld() {
		return MonsterSpawnBannedWorld;
	}
	public static ArrayList<String> getAnimalSpawnBannedWorld() {
		return AnimalSpawnBannedWorld;
	}
	public static int getClickItem() {
		return clickItem;
	}
	public static void setClickItem(int c) {
		clickItem = c;
	}
	public static  Location getO() {
		return o;
	}
	public static  void setO(Location location) {
		o = location;
	}
	
	public static  FileConfiguration getCfg() {
		return cfg;
	}
	public static File getF() {
		return f;
	}
	
	
	
	
	
	
public void start(){
	Server server = getServer();
	
	PluginManager manager = server.getPluginManager();
	
	Logger logger = getLogger();
	

}
	
	
	
	
	
	
	
	
	public void onEnable() {
		start();
		V=this.getDescription().getVersion();
		Logger lg = getLogger();
		Bukkit.getServer().getWorld("world").setSpawnFlags(false, false);
		ls = new AutoListener();
	getServer().getPluginManager().registerEvents(ls, this);
	cfg = Bukkit.getPluginManager().getPlugin(this.getName()).getConfig();
	f=new File(Bukkit.getPluginManager().getPlugin(this.getName()).getDataFolder().getAbsolutePath()+File.separator+"config.yml");
	file = new File(Bukkit.getPluginManager().getPlugin(this.getName()).getDataFolder().getAbsolutePath());
	file.mkdirs();
	if(!f.exists())
	{try {
		f.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
	cfg.set("Version",V);
	cfg.set("Tools", 50);
	cfg.set("MonsterSpawnBannedWorld",MonsterSpawnBannedWorld);
	cfg.set("AnimalSpawnBannedWorld",AnimalSpawnBannedWorld);
	cfg.createSection("MobModel");
	cfg.createSection("PointSpawn");
	cfg.createSection("WorldSpawn");
	cfg.createSection("Skill");
	try {
		saveYml();
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}else{
		try {
			cfg.load(f);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}
	
		loadYml();
		lg.info("---加载成功>>>");
		lg.info("||||||||||||||||||||||||||||||");
		lg.info("||||||\\\\|||||||||||//|||||||||");
		lg.info("|||||||\\\\|||||||||//||||||||||");
		lg.info("||||||||\\\\|||||||//|||||||||||");
		lg.info("|||||||||\\\\|||||//||||||||||||");
		lg.info("||||||||||\\\\|||//|||||||||||||");
		lg.info("|||||||||||\\\\|//||||||||||||||");
		lg.info("||||||||||||\\\\/|||||||||||||||");
		lg.info("||||||||||||||||||||||||||||||");
		
		
	
	for(int i=0;i<Spawn.getTHREADS();i++){
	tid.add(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Spawner(),Spawner.RUNS,Spawner.RUNS));
	}


	}


	public static void loadYml(){
		setClickItem(cfg.getInt("Tools"));
Skill.setSkills(new ArrayList<Skill> ());
Set<String>  all = cfg.getConfigurationSection("Skill").getKeys(false);
Object[] ary = all.toArray();	
for(int i = 0;i<ary.length;i++){
	Skill.newSkill( cfg.getConfigurationSection("Skill")
			.getConfigurationSection((String) ary[i]));
}

MobModel.setMobModel( new ArrayList<MobModel>());
all = cfg.getConfigurationSection("MobModel").getKeys(false);
ary = all.toArray();	
for(int i = 0;i<ary.length;i++){

new MobModel(
		
	 cfg.getConfigurationSection("MobModel")
	 .getConfigurationSection((String) ary[i]));
		
	}


Spawn.setSpawns( new ArrayList<Spawn>());
all = cfg.getConfigurationSection("PointSpawn").getKeys(false);
ary = all.toArray();	
Spawn.getSpawns().clear();
for(int i = 0;i<ary.length;i++){
new PointSpawn(
	 cfg.getConfigurationSection("PointSpawn")
	 .getConfigurationSection((String) ary[i]));
		
	}

all = cfg.getConfigurationSection("WorldSpawn").getKeys(false);
ary = all.toArray();	
for(int i = 0;i<ary.length;i++){
new WorldSpawn(
	 cfg.getConfigurationSection("WorldSpawn")
	 .getConfigurationSection((String) ary[i]));
		
	}






List<World> ws = Bukkit.getServer().getWorlds();
for(int i=0;i<ws.size();i++)
{boolean m = true;
boolean a = true;
	MonsterSpawnBannedWorld =(ArrayList<String>) cfg.getList("MonsterSpawnBannedWorld");
	AnimalSpawnBannedWorld=(ArrayList<String>) cfg.getList("AnimalSpawnBannedWorld");
	
	if(MonsterSpawnBannedWorld!=null)
	for(int r=0;r<MonsterSpawnBannedWorld.size();r++)
	{
		if(MonsterSpawnBannedWorld.get(r).equalsIgnoreCase(ws.get(i).getName())){
			m=false;
		}
	}
	
	if(AnimalSpawnBannedWorld!=null)
	for(int r=0;r<AnimalSpawnBannedWorld.size();r++)
	{
		if(AnimalSpawnBannedWorld.get(r).equalsIgnoreCase(ws.get(i).getName())){
			a=false;
		}
	}
	ws.get(i).setSpawnFlags(m, a);
	

}
	}

	public static void saveYml() throws IOException {
		
		cfg.set("Tools", clickItem);
		cfg.set("MonsterSpawnBannedWorld", MonsterSpawnBannedWorld);
		cfg.set("AnimalSpawnBannedWorld", AnimalSpawnBannedWorld);

for(int i=0 ;i<MobModel.getMobModels().size();i++){
	MobModel.getMobModels().get(i).save();
}

for(int i=0 ;i<Spawn.getSpawns().size();i++){
	Spawn.getSpawns().get(i).save();
}
		
for(int i=0 ;i<Skill.getSkills().size();i++){
	Skill.getSkills().get(i).save();
}		
		
		
		
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static String getV(){
		return V;
	}
	
	public void onDisable() {
		Mob.killAll();
		Logger lg = getLogger();
		lg.info("<<<卸载成功---");
	}


	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		
		
		
		
		
		if ((sender instanceof Player))
		if (cmd.getName().equalsIgnoreCase("Mobs")) {
			Player p=(Player) sender;
			if(args.length==0)
			{
p.sendMessage("§c[Mobs]§5Version:"+V+"\n"+"§c[Mobs]§5更多帮助请参照 /Mobs help");
				
				
				return true;
			}
			try {
				return Cmd.mobSpawn(p,args);
			} catch (Exception e) {
	
				e.printStackTrace();
				p.sendMessage("插件报错!");
				return true;
			}
		}else{
return false;
		
		}
		
	sender.sendMessage("本插件不支持控制台操作!");
		return true;
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static ItemStack getVoidim() {
		ItemStack zz = new ItemStack(8);
		ItemMeta im = zz.getItemMeta();
		im.setDisplayName(" ");
		zz.setItemMeta(im);
		return zz;
	}
	public static ItemStack getDropI(int i) {
		ItemStack zz = new ItemStack(362);
		ItemMeta im = zz.getItemMeta();
		im.setDisplayName("§c掉落几率:"+i+"%");
		zz.setItemMeta(im);
		return zz;
	}

	


}







