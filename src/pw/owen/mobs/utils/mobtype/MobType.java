package pw.owen.mobs.utils.mobtype;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.run.Main;
import pw.owen.mobs.utils.JarLoad;
import pw.owen.mobs.utils.mobtype.example.MobType_CanSize;

public abstract class MobType {
	// public static final MobType a = new MobType("", null);
	private static MobType[] values = null;
	private String type = null;
	private EntityType eType = null;
	static {
		values = regValues();
	}


	public String getName() {

		return type;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return getName();
	}
	protected MobType(String type, EntityType eType) {

		this.type = type;
		this.eType = eType;
	}



	protected LivingEntity modify1(LivingEntity e) {
		
		if (e instanceof Ageable)
 {
			((Ageable) e).setAgeLock(true);
			((Ageable) e).setAdult();
		}
		
		e.getEquipment().setBoots(null);
		e.getEquipment().setChestplate(null);
		e.getEquipment().setHelmet(null);
		e.getEquipment().setItemInHand(null);
		e.getEquipment().setLeggings(null);
		e.getEquipment().setBootsDropChance(0);
		e.getEquipment().setChestplateDropChance(0);
		e.getEquipment().setHelmetDropChance(0);
		e.getEquipment().setItemInHandDropChance(0);
		e.getEquipment().setLeggingsDropChance(0);
		return modify(e);
	}

	protected abstract LivingEntity modify(LivingEntity e);

	public static double getStartWith() {
		return 1.7;
	}
	public static MobType fromName(String str) {
		for (int i = 0; i < values.length; i++)
			if (values[i].getName().equalsIgnoreCase(str))
				return values[i];
		return null;
	}

	public static MobType[] values() {
		return values;
	}

	private static MobType[] regValues() {
		try {
			List<Class<?>> cs = JarLoad.getJarClass(Main.getMain()
					.getMainFile().getAbsolutePath(), MobType.class,
					Main.getCLoader());
			List<MobType> ls = new ArrayList<MobType>();

			for (int i = 0; i < cs.size(); i++)
				if (Double.parseDouble(cs.get(i)
						.getMethod("getStartWith", null).invoke(null, null)
						.toString()) <= Main.bukkitVer)
					ls.add((MobType) cs.get(i).newInstance());

				
			MobType[] vl = new MobType[ls.size()];
			vl = ls.toArray(vl);

			
			return vl;
		} catch (ClassNotFoundException | IOException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
			return new MobType[0];
		}

	}

	public boolean canSize() {
		if (this.getClass().getSuperclass() == MobType_CanSize.class)
			return true;

		return false;
	}

	public static LivingEntity create(MobType type, Location loc) {
		return type.modify1((LivingEntity) loc.getWorld().spawnEntity(loc,
				type.eType));
	}

	public static LivingEntity create(MobType type, Location loc, int size) {
		if (!(type instanceof MobType_CanSize))
			return null;

		MobType_CanSize t2 = (MobType_CanSize) type;

		return t2.modify2(
				(LivingEntity) loc.getWorld().spawnEntity(loc, type.eType),
				size);
	}

}
