package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Blaze extends MobType {
	public MobType_Blaze() {
		super("¡“—Ê»À", EntityType.BLAZE);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
