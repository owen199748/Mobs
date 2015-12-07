package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Wither extends MobType {
	public MobType_Wither() {
		super("µÚ¡„", EntityType.WITHER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
