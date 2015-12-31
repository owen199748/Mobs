package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Enderman extends MobType {
	public MobType_Enderman() {
		super("Ä©Ó°ÈË", EntityType.ENDERMAN);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
