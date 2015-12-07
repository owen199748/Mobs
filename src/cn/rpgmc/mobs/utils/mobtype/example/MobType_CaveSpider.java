package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_CaveSpider extends MobType {
	public MobType_CaveSpider() {
		super("∂¥—®÷©÷Î", EntityType.CAVE_SPIDER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
