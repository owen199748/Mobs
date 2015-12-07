package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Wolf_Little extends MobType {
	public MobType_Wolf_Little() {
		super("Ð¡ÀÇ", EntityType.WOLF);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Wolf w = (Wolf)e;
		w.setBaby();
		w.setAngry(false);
		return e;
	}

}
