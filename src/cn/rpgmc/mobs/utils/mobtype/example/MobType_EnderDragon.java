package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_EnderDragon extends MobType {
	public MobType_EnderDragon() {
		super("Ä©Ó°Áú", EntityType.ENDER_DRAGON);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		EnderDragon d = (EnderDragon) e;
		// d.getParts().add(arg0);

		return e;
	}

}
