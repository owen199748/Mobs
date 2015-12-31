package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Giant extends MobType {
	public MobType_Giant() {
		super("æﬁ»À", EntityType.GIANT);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
