package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Bat extends MobType {
	public MobType_Bat() {
		super("����", EntityType.BAT);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
