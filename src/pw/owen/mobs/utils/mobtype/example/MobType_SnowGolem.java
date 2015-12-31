package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_SnowGolem extends MobType {
	public MobType_SnowGolem() {
		super("Ñ©¿þÀÜ", EntityType.SNOWMAN);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		return e;
	}

}
