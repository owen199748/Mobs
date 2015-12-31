package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_CaveSpider extends MobType {
	public MobType_CaveSpider() {
		super("∂¥—®÷©÷Î", EntityType.CAVE_SPIDER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
