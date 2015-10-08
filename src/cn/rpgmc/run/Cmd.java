package cn.rpgmc.run;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;











import com.avaje.ebeaninternal.api.ClassUtil;

import cn.rpgmc.bean.integer.Damage;
import cn.rpgmc.bean.integer.EXP;
import cn.rpgmc.bean.integer.HP;
import cn.rpgmc.bean.mob.DropItemStack;
import cn.rpgmc.bean.mob.Eqpt;
import cn.rpgmc.bean.mob.Mob;
import cn.rpgmc.bean.mob.MobModel;
import cn.rpgmc.bean.skill.Skill;
import cn.rpgmc.bean.spawn.PointSpawn;
import cn.rpgmc.bean.spawn.Spawn;
import cn.rpgmc.bean.spawn.WorldSpawn;

public class Cmd {
	

		
static boolean mobSpawn(Player p,String[] args) throws Exception{

	if(args.length>0){
if(args[0].equalsIgnoreCase("spawn")){		
	if(args.length==1){
		spawnHelp(p);
	if(Main.getsSpawn()==null)
		return true;
	else if(Main.getsSpawn().getCreateType().equalsIgnoreCase(Spawn.POINTMOBCREATE))
		pointSpawnHelp(p);
	else if(Main.getsSpawn().getCreateType().equalsIgnoreCase(Spawn.WORLDMOBCREATE))
		worldSpawnHelp(p);
	
 return true;
}
			return spawn(p,args);
}else if(args[0].equalsIgnoreCase("mob")){
	if(args.length==1)
		{mobHelp(p);
		return true;}
	return mob(p,args);
}else if(args[0].equalsIgnoreCase("set")){

	if(args.length==1){
		if(p.getItemInHand()==null|p.getItemInHand().getType().getId()==Material.AIR.getId()){
			p.sendMessage("��c[����������]��f����һ����Ʒ��������֮����ִ�и�����.");
			return true;	
			
		}
		
		Main.setClickItem(p.getItemInHand().getTypeId());
		p.sendMessage("��c[����������]��f���Ѿ���"+p.getItemInHand().getType().name()+"��Ϊ��ѡ����.");
		return true;
	}else{
		return false;
	}

}else if(args[0].equalsIgnoreCase("setban")){

	if(args.length!=3){
		return false;
	}else{
		if(args[1].equalsIgnoreCase("Animal")){
			if(args[2].equalsIgnoreCase("true")){
				for(int i =0;i<Main.getAnimalSpawnBannedWorld().size();i++){
					if(Main.getAnimalSpawnBannedWorld().get(i).equalsIgnoreCase(p.getWorld().getName()))
					{
						Main.getAnimalSpawnBannedWorld().remove(p.getWorld().getName());
					}

				}
				
				Main.getAnimalSpawnBannedWorld().add(p.getWorld().getName());
			}else if(args[2].equalsIgnoreCase("false")){
				Main.getAnimalSpawnBannedWorld().remove(p.getWorld().getName());
			}else{
				return false;
			}
			
		}else if(args[1].equalsIgnoreCase("Monster")){
			if(args[2].equalsIgnoreCase("true")){
				for(int i =0;i<Main.getMonsterSpawnBannedWorld().size();i++){
					if(Main.getMonsterSpawnBannedWorld().get(i).equalsIgnoreCase(p.getWorld().getName()))
					{
						Main.getMonsterSpawnBannedWorld().remove(p.getWorld().getName());
					}
					
				}
				
				Main.getMonsterSpawnBannedWorld().add(p.getWorld().getName());

			}else if(args[2].equalsIgnoreCase("false")){
				Main.getMonsterSpawnBannedWorld().remove(p.getWorld().getName());
			}else{
				return false;
			}
			
			
		}else{
			return false;
		}
		
		try {
			Main.saveYml();
			p.sendMessage("��c[����������]��f���óɹ�.");
			return true;
		} catch (IOException e) {
			p.sendMessage("��c[����������]��f���ñ���ʧ��.");
			return true;
		}

		
		
	}
	
}else if(args[0].equalsIgnoreCase("listban")){
	String s1="��ֹ��������:";
	String s2="��ֹ��������:";
	ArrayList<String> al = Main.getAnimalSpawnBannedWorld();
	ArrayList<String> ml = Main.getMonsterSpawnBannedWorld();
	for(int i =0; i<al.size();i++){
		s1+=al.get(i);
		if(i!=al.size()-1){
			s1+=",";
		}
	}
	
	for(int i =0; i<ml.size();i++){
		s2+=ml.get(i);
		if(i!=ml.size()-1){
			s2+=",";
		}
	}
	p.sendMessage(s1);
	p.sendMessage(s2);
}else if(args[0].equalsIgnoreCase("reload")){
	if(args.length==1){
		try {
			Main.getCfg().set("Version",Main.getV());
			Main.loadYml();
			p.sendMessage("��c[����������]��f�������سɹ�.");
		} catch (Exception e) {
			e.printStackTrace();
			p.sendMessage("��c[����������]��f��������ʧ��,�����Գ���ɾ�����������������������������ļ�.");
		}
	}else{
		return false;
	}
	return true ;
}else if(args[0].equalsIgnoreCase("help")){
	if(args.length==1){
		p.sendMessage("��c[����������]��f/Mobs help [main/mob/spawn] (spawn)<Point/World> �鿴[������/��������/ˢ�µ�����]�İ����ı�");
	return true;
	}
if(args.length!=2){
	if(args.length>2&args[1].equalsIgnoreCase("spawn"));
	else
	return false;
}
if(args[1].equalsIgnoreCase("main")){
mainHelp(p);
}else if(args[1].equalsIgnoreCase("mob")){
mobHelp(p);
}else if(args[1].equalsIgnoreCase("spawn")){

if(args.length==2){
	p.sendMessage("��c[����������]��f/Mobs help spawn <Point/World> �鿴[ˢ�µ�����]�İ����ı�");
	return true;
}
if(!args[2].equalsIgnoreCase("point")&!args[2].equalsIgnoreCase("world")){
	p.sendMessage("��c[����������]��f/Mobs help spawn <Point/World> �鿴[ˢ�µ�����]�İ����ı�");
	return true;
}
	//����ˢ�µ�	

spawnHelp(p);
					if(args[2].equalsIgnoreCase("point")){
pointSpawnHelp(p);
					}				
					if(args[2].equalsIgnoreCase("world")){
worldSpawnHelp(p);
					}

}else{
	return false;
}
return true;

	
}else if(args[0].equalsIgnoreCase("skill")){
	return skill(args,p);
}
	else{
		
		return false;
	}
	}
	
	return false;
	
}

private static void worldSpawnHelp(Player p) {
	p.sendMessage("��a  /Mobs spawn modify world [add/list/del] �޸�ˢ�µ�����");
	p.sendMessage("��a  /Mobs spawn modify chance [����] �޸�ˢ�µļ���");
	p.sendMessage("��a  /Mobs spawn modify playerNearby [����] �޸�����ҵ�ˢ��������");
	
}

private static void pointSpawnHelp(Player p) {
	p.sendMessage("��a  /Mobs spawn modify point ����ˢ�µ�λ��");
	p.sendMessage("��a  /Mobs spawn modify single [Single] ����ÿ��ˢ������");
	p.sendMessage("��a  /Mobs spawn modify range [Range] ���û�뾶(�����ᱻ����ԭ��)");
	p.sendMessage("��a  /Mobs spawn modify center ���û�뾶��Բ�ĵ�");
	
}

private static void spawnHelp(Player p) {
	p.sendMessage("��a  /Mobs spawn new [Point/World] [ˢ�µ���] ����ĳ��ˢ�µ�(�Զ�select)");
	p.sendMessage("��a  /Mobs spawn select [Point/World] [ˢ�µ���] ����ĳ��ˢ�µ������");
	p.sendMessage("��a  /Mobs spawn list [Point/World] �鿴ˢ�µ��б�");
	p.sendMessage("��a  /Mobs spawn killall ɾ��һ��ˢ�µ��ˢ������");
	p.sendMessage("��a  /Mobs spawn see [ˢ�µ�] �鿴һ������ˢ�µ����ϸ��Ϣ");
	p.sendMessage("��a  /Mobs spawn modify del [ˢ�µ�] ɾ��һ������ˢ�µ�");
	p.sendMessage("��a  /Mobs spawn modify mob [sName] ���ù���ģ��");
	p.sendMessage("��a  /Mobs spawn modify lag [Lag] ����ˢ�¼��(tick:20tick=1s)");
	p.sendMessage("��a  /Mobs spawn modify max [Max] ���ù����������(����ˢ��Ϊ�����������)");
	
}

private static void mobHelp(Player p) {
	p.sendMessage("��a  /Mobs mob new [������(������ʾ����,��Ϊ�Ǻ�)] ����ĳ������(�Զ�select)");
	p.sendMessage("��a  /Mobs mob select [������] ����ĳ�����������");
	p.sendMessage("��a  /Mobs mob spawn �����ߴ�����һ���ù���");
	p.sendMessage("��a  /Mobs mob see �鿴һ���������ϸ��Ϣ");
	p.sendMessage("��a  /Mobs mob list  �鿴�����б�");
	p.sendMessage("��a  /Mobs mob modify del ɾ��һ������");
	p.sendMessage("��a  /Mobs mob modify drops [add/list/del] ���ӵ�����͵��伸��");
	p.sendMessage("��a  /Mobs mob modify droptype [��ʽ(All,Invalid,Random)] ���õ��䷽ʽ");
	p.sendMessage("��a  /Mobs mob modify name [Name] ��������");
	p.sendMessage("��a  /Mobs mob modify hp [HighHP] <LowHP>����Ѫ��");
	p.sendMessage("��a  /Mobs mob modify attrcover [boolean] �����Ƿ񸲸�����");
	p.sendMessage("��a  /Mobs mob modify damage [HighDamage] <LowDamage> �����˺�");
	p.sendMessage("��a  /Mobs mob modify exp [HighEXP] <LowEXP> ������������ľ���");
	p.sendMessage("��a  /Mobs mob modify type [Type] ���ù�������");
	p.sendMessage("��a  /Mobs mob modify eqpt ����װ��Ϊ��ǰ������װ�������õ�����");
	p.sendMessage("��a  /Mobs mob modify sl [Day/Night/Sun/Rain/Thunder] [true/false] ���ù���ˢ�¶Ի���������");
	
}

private static void mainHelp(Player p) {
	p.sendMessage("��a  /Mobs set ����ѡ��������ƷIDΪ���ϵ���Ʒ");
	p.sendMessage("��a  /Mobs spawn ����ˢ�µ�ĸ�������<��Ҫ����>");
	p.sendMessage("��a  /Mobs mob ���ù���ĸ�������<��Ҫ����>");
	p.sendMessage("��a  /Mobs skill ���ü��ܵĸ�������<��Ҫ����>");
	p.sendMessage("��a  /Mobs setban [Animal/Monster] [true/false] ���������ڵ������Ƿ����Ĭ�ϲ����Ķ���/����");
	p.sendMessage("��a  /Mobs listban �鿴�����ڵ������Ƿ����Ĭ�ϲ����Ķ���/����");
	p.sendMessage("��a  /Mobs reload ���ز��");
	p.sendMessage("��a  /Mobs help ����");
	
}

private static boolean skill(String[] args, Player p) {
	// TODO �Զ����ɵķ������
	p.sendMessage("���������ڴ��¸��汾!");
	return true;
}



private static boolean mob(Player p, String[] args) {
	 if(args[1].equalsIgnoreCase("select")){
		 if(args.length==2){
				p.sendMessage("��a  /Mobs mob select  [������] ����ĳ�����������");
			 return true;
		 }
		if(args.length!=3){
			return false;
		}
		

	ConfigurationSection section = Main.getCfg().getConfigurationSection("MobModel").getConfigurationSection(args[2]);
			if(section==null)
			{
				p.sendMessage("��c[����������]��f�ù��ﲻ����.");
				return true;
			}
			Main.setsMobModel(new MobModel(section));
			p.sendMessage("��c[����������]��f�Ѿ�ѡ�����:"+args[2]+".");
			return true;
		
	}else if(args[1].equalsIgnoreCase("new")){
		if(args.length!=3)
			return false;
		try {
			Main.setsMobModel(new MobModel(args[2],Main.getCfg().getConfigurationSection("MobModel")));
			Main.saveYml();
			p.sendMessage("��c[����������]��f�����ɹ�.");
		} catch (IOException e) {
			p.sendMessage("��c[����������]��f����ʧ��.");
		}
		return true;
		
	}else if(args[1].equalsIgnoreCase("list")){
		 String s="�����б�:";
		for(int i=0;i<MobModel.getMobModel().size();i++){
			s+=MobModel.getMobModel().get(i).getsName();
			if(i!=MobModel.getMobModel().size()-1){
				s+=",";
			}
		}
		p.sendMessage(s);
		return true;
	}else if(Main.getsMobModel()==null){
		 p.sendMessage("��c[����������]��f����ʹ��/Mobs mob select [������] ѡ��ĳ����������޸�.");
return true;
	}
	 MobModel mm=Main.getsMobModel();
	 
	 if(args[1].equalsIgnoreCase("see")){
		 p.sendMessage(mm.getSee());
		 return true;
	}else if(args[1].equalsIgnoreCase("spawn")){
		if(args.length!=2)
			return false;
		
		mm.spawnMob(p.getEyeLocation());
		p.sendMessage("��c[����������]��f�����ɹ�.");
		
	}else if(args[1].equalsIgnoreCase("modify"))	{
		return mobModify(p,args);
	}


	return false;
}

private static boolean mobModify(Player p, String[] args) {
	MobModel mm=Main.getsMobModel();
	 if(args[2].equalsIgnoreCase("del")){
		if(!mm.remove())
			p.sendMessage("��c[����������]��f�ù��ﲻ����.");	
		Main.setsMobModel(null);
		p.sendMessage("��c[����������]��f�����ɹ�.");
	 }	 
else if(args[2].equalsIgnoreCase("drops")){
		 if(args.length<4){
			 return false;	
			 }
		 if(args[3].equalsIgnoreCase("add")){
			 
			 if(p.getItemInHand()==null)
			 {	 p.sendMessage("��c[����������]��f���ֳ�һ��װ��.");
			 return true;
			 	}
			 
			 if(args.length!=5){
				 return false;
			 }
			 
			 mm.addDrop(p.getItemInHand(), Integer.parseInt(args[4]));
			 
		 }else if(args[3].equalsIgnoreCase("list")){
			 
			 String s="�������б�:";
			 ArrayList<DropItemStack> a = mm.getDrop();
			 for(int i=0;i<a.size();i++){
				 s+=i+":"+a.get(i).getItem().getType().name()+"|"+a.get(i).getI();
				 if(i!=a.size()-1){
					 s+=",";
				 }
			 }
			 
			 p.sendMessage(s);
			 
			 
			 
		 }else if(args[3].equalsIgnoreCase("del")){
			 if(args.length!=5){
				 return false;
			 }
			 
			 if(!mm.delDrop( Integer.parseInt(args[4]))){
				 p.sendMessage("��c[����������]��f�õ����ﲻ����.");
				 return true;
			 }
		 }
		 

		 
	

	 } else if(args[2].equalsIgnoreCase("sl")){
		 if(args.length!=5)
			 return false;
					 boolean t = false;
					 if(args[4].equalsIgnoreCase("true"))
					 t=true;
					 else
						 if(args[4].equalsIgnoreCase("false"))
							 t=false;
						 		else
							 return false;
		 
					 if(args[3].equalsIgnoreCase("Night")){
						 mm.getSurvivalLimit().isNight(t);
					 }else if(args[3].equalsIgnoreCase("Rain")){
						 mm.getSurvivalLimit().isRain(t);
					 }else if(args[3].equalsIgnoreCase("Thunder")){
						 mm.getSurvivalLimit().isThundering(t);
					 }else if(args[3].equalsIgnoreCase("Day")){
						 mm.getSurvivalLimit().isDay(t);
					 }else if(args[3].equalsIgnoreCase("Sun")){
						 mm.getSurvivalLimit().isSun(t);
					 }else {
						 return false;
					 }
		 

	 }else if(args[2].equalsIgnoreCase("droptype")){
		 if(args.length!=4)
			 return false;
		 
		 if(args[3].equalsIgnoreCase("All"))
		 {
			mm.setDropType(1);
		 }
		 else if(args[3].equalsIgnoreCase("Invalid"))
		 {
				mm.setDropType(0);
		 }else if(args[3].equalsIgnoreCase("Random"))
		 {
				mm.setDropType(2);
		 }else
			 return false;
		
	 }else if(args[2].equalsIgnoreCase("name")){
		 if(args.length!=4)
			 return false;
		 mm.setDisplayName(args[3]);
	 }else if(args[2].equalsIgnoreCase("damage")){
		 if(args.length==4){
				mm.setDmg(new Damage(Integer.parseInt(args[3]))); 
		 }else if(args.length==5){
				mm.setDmg(new Damage(Integer.parseInt(args[3]),Integer.parseInt(args[4]))); 
		 }else {return false;}
		 
	 }else if(args[2].equalsIgnoreCase("hp")){
		 if(args.length==4){
				mm.setHp(new HP(Integer.parseInt(args[3]))); 
		 }else if(args.length==5){
				mm.setHp(new HP(Integer.parseInt(args[3]),Integer.parseInt(args[4]))); 
		 }else {return false;}
	 }else if(args[2].equalsIgnoreCase("attrcover")){
		 if(args.length!=4)
			 return false;
		 if(args[3].equalsIgnoreCase("true"))
		 mm.setAttrCover(true);
		 else if(args[3].equalsIgnoreCase("false"))
		mm.setAttrCover(false);
		 else
			 return false;
	 
	 }else if(args[2].equalsIgnoreCase("exp")){
		 if(args.length==4){
				mm.setExp(new EXP(Integer.parseInt(args[3]))); 
		 }else if(args.length==5){
				mm.setExp(new EXP(Integer.parseInt(args[3]),Integer.parseInt(args[4]))); 
		 }else {return false;}
	 }else if(args[2].equalsIgnoreCase("type")){
		 if(args.length!=4)
			 return false;
		mm.setType(EntityType.fromName(args[3]));
	 }else if(args[2].equalsIgnoreCase("eqpt")){
		 mm.setEqpt(new Eqpt(p.getEquipment().getHelmet(),p.getEquipment().getChestplate(),p.getEquipment().getLeggings(),p.getEquipment().getBoots(),p.getEquipment().getItemInHand()));
		//װ��
	 }else{
		 return false;
	 }
	
try {
	saveMobModel(mm, p);
	 p.sendMessage("��c[����������]��f�����ɹ�.");
} catch (IOException e) {
	 p.sendMessage("��c[����������]��f����ʧ��.");
} 
return true;
}

private static boolean spawn(Player p, String[] args) {

	
	 if(args[1].equalsIgnoreCase("select")){
		if(args.length!=4){
			return false;
		}
		ConfigurationSection section = null;
		if(args[2].equalsIgnoreCase("Point")){
			section = Main.getCfg().getConfigurationSection("PointSpawn").getConfigurationSection(args[3]);
			if(section==null)
			{
				p.sendMessage("��c[����������]��f�õ㲻����.");
				return true;
			}
			Main.setsSpawn(new PointSpawn(section));
			p.sendMessage("��c[����������]��f�Ѿ�ѡ���:"+args[3]+".");
			return true;
		}else if(args[2].equalsIgnoreCase("World")){
			section = Main.getCfg().getConfigurationSection("WorldSpawn").getConfigurationSection(args[3]);
			if(section==null)
			{
				p.sendMessage("��c[����������]��f�õ㲻����.");
				return true;
			}
			Main.setsSpawn(new WorldSpawn(section));
			p.sendMessage("��c[����������]��f�Ѿ�ѡ���:"+args[3]+".");
			return true;
		}
	}else if(args[1].equalsIgnoreCase("new")){
		
	if(args.length!=4)
		return false;
	if(args[2].equalsIgnoreCase("Point")){
		if(Main.getO()==null)
		{
			p.sendMessage("��c[����������]��f����ѡ��һ����.");
			return true;
		}
		Main.setsSpawn(new PointSpawn(args[3],Main.getCfg().getConfigurationSection("PointSpawn"),Main.getO()));
		Main.getsSpawn().save();
		p.sendMessage("��c[����������]��f�����ɹ�.");
		try {
			Main.saveYml();
		} catch (IOException e) {
			p.sendMessage("��c[����������]��f����ʧ��.");
		}
	return true;
	}
	else if(args[2].equalsIgnoreCase("World")){
		ArrayList<World> w = new ArrayList<World>();
		w.add(p.getWorld());
		Main.setsSpawn(new WorldSpawn(args[3],Main.getCfg().getConfigurationSection("WorldSpawn"),w));
		Main.getsSpawn().save();
		p.sendMessage("��c[����������]��f�����ɹ�.");
		try {
			Main.saveYml();
		} catch (IOException e) {
			p.sendMessage("��c[����������]��f����ʧ��.");
		}
		return true;
	}
	
		
	}else if(args[1].equalsIgnoreCase("list")){
		if (args.length!=3)
			return false;

		 String s="";
		 if(args[2].equalsIgnoreCase("world")){
			 s="����ˢ�µ��б�:";
			 
				for(int i=0;i<WorldSpawn.getWmobcreates().size();i++){
					s+=WorldSpawn.getWmobcreates().get(i).getcName();
					if(i!=WorldSpawn.getWmobcreates().size()-1){
						s+=",";
					}
				}
				
				
		 }else if(args[2].equalsIgnoreCase("point")){
			 s="����ˢ�µ��б�:";
			 
			 
				for(int i=0;i<PointSpawn.getPmobcreates().size();i++){
					s+=PointSpawn.getPmobcreates().get(i).getcName();
					if(i!=PointSpawn.getPmobcreates().size()-1){
						s+=",";
					}
				}
			 
		 }
		 p.sendMessage(s);
		 return true;

	}else if(Main.getsSpawn()==null){
		 p.sendMessage("��c[����������]��f����ʹ��/Mobs spawn select [Point/World] [ˢ�µ���] ѡ��ĳ��ˢ�µ�����޸�.");
		 return true;
	 }
	
	 
	 
	 Spawn spawn = Main.getsSpawn();
	 
	if(args[1].equalsIgnoreCase("modify"))
	{
		return spawnModify(p,args);
	}else if(args[1].equalsIgnoreCase("killall")){
	
			Main.getsSpawn().killAll();
			p.sendMessage("��c[����������]��fִ�гɹ�.");
			return true;
		
		
	}else if(args[1].equalsIgnoreCase("see")){
		p.sendMessage(spawn.getSee());
		return true;
	}else{
		return false;
	}




	
	

}

private static boolean spawnModify(Player p, String[] args) {
	if(args.length<3){
		return false;
	}
	if(Main.getsSpawn() instanceof PointSpawn){
		PointSpawn pSpawn = (PointSpawn)Main.getsSpawn();
if(args[2].equalsIgnoreCase("point")){
	if(Main.getO()==null){

		p.sendMessage("��c[����������]��f����ʹ��ѡ����ѡ��һ����.");
		return true;
		///
	}
	
	pSpawn.setP(Main.getO());
	p.sendMessage("��c[����������]��f���Ѿ��������µ�ˢ�µ�.");
	saveSpawn(pSpawn,p);
	return true;
}else 	if(args[2].equalsIgnoreCase("single")){
	if(args.length!=4){
		return false;
	}
	pSpawn.setOne(Integer.parseInt(args[3]));
	p.sendMessage("��c[����������]��f���óɹ�.");
	return true;

}else if(args[2].equalsIgnoreCase("range")){
	if(args.length!=4){
		return false;
	}
	pSpawn.setRange(Integer.parseInt(args[3]));
	p.sendMessage("��c[����������]��f���Ѿ��������µĻ�뾶.");
	saveSpawn(pSpawn,p);
	return true;
}else if(args[2].equalsIgnoreCase("center")){
	if(Main.getO()==null){

		p.sendMessage("��c[����������]��f����ʹ��ѡ����ѡ��һ����.");
		return true;
		///
	}
pSpawn.setO(Main.getO());
p.sendMessage("��c[����������]��f���Ѿ��������µ�Բ�ĵ�.");
saveSpawn(pSpawn,p);
return true;
}


	}else if(Main.getsSpawn() instanceof WorldSpawn){
		WorldSpawn wSpawn = (WorldSpawn)Main.getsSpawn();
		if(args[2].equalsIgnoreCase("world")){
			if(args.length!=5){
				return false;
			}
			if(args[3].equalsIgnoreCase("add")){
				for(int i=0;i<wSpawn.getWorld().size();i++){
					if(wSpawn.getWorld().get(i).getName().equalsIgnoreCase(args[4])){
						p.sendMessage("��c[����������]��f�������Ѵ���.");
						return true;
					}
				}
				if(Bukkit.getWorld(args[4])!=null){
				wSpawn.getWorld().add(Bukkit.getWorld(args[4]));
				p.sendMessage("��c[����������]��f�����ɹ�.");}
				else {
					p.sendMessage("��c[����������]��f�����粻����.");
				}
			}else if(args[3].equalsIgnoreCase("del")){
				for(int i=0;i<wSpawn.getWorld().size();i++){
					if(wSpawn.getWorld().get(i).getName().equalsIgnoreCase(args[4])){
						wSpawn.getWorld().remove(i);
						p.sendMessage("��c[����������]��f�����ɹ�.");
						return true;
					}
				}
				
				p.sendMessage("��c[����������]��f�����粻����.");
				return true;
			}else if(args[3].equalsIgnoreCase("list")){
				String s="ˢ�������б�:\n";
				for(int i=0;i<wSpawn.getWorld().size();i++){
					s+=wSpawn.getWorld().get(i).getName();
					if(i!=wSpawn.getWorld().size()-1){
					s+=",";}
				}
				p.sendMessage(s);
			}
			p.sendMessage("��a  /Mobs spawn modify world [add/list/del] �޸�ˢ�µ�����");
		
			p.sendMessage("��c[����������]��f.");
			saveSpawn(wSpawn,p);
			return true;
		}else if(args[2].equalsIgnoreCase("chance")){
		if(args.length!=4){
			return false;
		}
		wSpawn.setChance(Double.parseDouble(args[3]));
			p.sendMessage("��c[����������]��f���Ѿ��������µ�ˢ�¼���.");
			saveSpawn(wSpawn,p);
			return true;
		}else if(args[2].equalsIgnoreCase("playerNearby")){
			if(args.length!=4){
				return false;
			}
			
			
			wSpawn.setPlayerNearby(Integer.parseInt(args[3]));
			p.sendMessage("��c[����������]��f���óɹ�.");
			saveSpawn(wSpawn,p);
			return true;
			
			
			
		}


	}
	
	
	
	
	
	///����Ϊ��������
	Spawn spawn = Main.getsSpawn();
	if(args[2].equalsIgnoreCase("del")){
		spawn.remove();
		Main.setsSpawn(null);
		p.sendMessage("��c[����������]��f���Ѿ�ɾ���˸õ�.");
	}else 		if(args[2].equalsIgnoreCase("lag")){
		if(args.length!=4){
			return false;
		}
		spawn.setTime(Integer.parseInt(args[3]));
		p.sendMessage("��c[����������]��f���óɹ�.");
	}else 		if(args[2].equalsIgnoreCase("max")){
		if(args.length!=4){
			return false;
		}
		spawn.setAll(Integer.parseInt(args[3]));
		p.sendMessage("��c[����������]��f���óɹ�.");
	}else 	if(args[2].equalsIgnoreCase("Mob")){
		
		if(args.length!=4){
			return false;
		}
		if(MobModel.isMobModel(args[3])==-1){
			p.sendMessage("��c[����������]��f���ﲻ����.");
			return true;
		}
		Main.getsSpawn().setMm(MobModel.getMobModel().get(MobModel.isMobModel(args[3])));
		p.sendMessage("��c[����������]��f���óɹ�.");
	}else
		return false;
	
	

	saveSpawn(spawn,p);
	return true;
}

private static void saveSpawn(Spawn pSpawn, Player p) {
	pSpawn.save();
	try {
		Main.saveYml();
	} catch (IOException e) {
		p.sendMessage("��c[����������]��f���ñ���ʧ��.");
	}
	
}


private static void saveMobModel(MobModel mm, Player p) throws IOException {
	mm.save();
	try {
		Main.saveYml();
	} catch (IOException e) {
		p.sendMessage("��c[����������]��f���ñ���ʧ��.");
	}
	
}
}

