package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_Guardian_Elder extends MobType {
	public MobType_Guardian_Elder() {
		super("‘∂π≈ ÿŒ¿’ﬂ", EntityType.GUARDIAN);
	}

	public static double getStartWith() {
		return 1.8;
	}
	@Override
	public LivingEntity modify(LivingEntity e) {
		Guardian g = (Guardian) e;
		g.setElder(true);
		return e;
	}

}
