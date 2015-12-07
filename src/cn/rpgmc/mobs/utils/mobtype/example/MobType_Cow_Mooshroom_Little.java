package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Cow_Mooshroom_Little extends MobType {
	public MobType_Cow_Mooshroom_Little() {
		super("С�蹽", EntityType.MUSHROOM_COW);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		((Ageable) e).setBaby();
		return e;
	}

}
