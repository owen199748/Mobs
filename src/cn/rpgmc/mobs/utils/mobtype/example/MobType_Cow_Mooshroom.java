package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Cow_Mooshroom extends MobType {
	public MobType_Cow_Mooshroom() {
		super("ßè¹½", EntityType.MUSHROOM_COW);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
