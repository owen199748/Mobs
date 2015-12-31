package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Creeper extends MobType {
	public MobType_Creeper() {
		super("≈¿––’ﬂ", EntityType.CREEPER);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Creeper c = (Creeper) e;
		c.setPowered(false);
		return e;
	}

}
