package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Zombie extends MobType {
	public MobType_Zombie() {
		super("½©Ê¬", EntityType.ZOMBIE);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Zombie z = (Zombie) e;
		z.setBaby(false);
		z.setVillager(false);
		return e;
	}

}
