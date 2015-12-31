package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Wither extends MobType {
	public MobType_Wither() {
		super("µÚ¡„", EntityType.WITHER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
