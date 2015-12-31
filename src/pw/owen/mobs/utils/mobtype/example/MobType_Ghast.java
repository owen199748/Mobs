package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Ghast extends MobType {
	public MobType_Ghast() {
		super("¶ñ»ê", EntityType.GHAST);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
