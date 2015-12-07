package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Wolf_Angry extends MobType {
	public MobType_Wolf_Angry() {
		super("·ßÅ­µÄÀÇ", EntityType.WOLF);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Wolf w = (Wolf)e;
		w.setAdult();
		w.setAngry(true);
		return e;
	}

}
