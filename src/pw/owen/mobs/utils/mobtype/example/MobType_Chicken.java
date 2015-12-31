package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Chicken extends MobType {
	public MobType_Chicken() {
		super("¼¦", EntityType.CHICKEN);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
