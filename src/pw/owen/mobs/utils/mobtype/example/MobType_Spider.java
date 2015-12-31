package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Spider extends MobType {
	public MobType_Spider() {
		super("÷©÷Î", EntityType.SPIDER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
