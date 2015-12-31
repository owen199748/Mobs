package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Pig extends MobType {
	public MobType_Pig() {
		super("÷Ì", EntityType.PIG);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
