package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_ZombiePigman_Little extends MobType {
	public MobType_ZombiePigman_Little() {
		super("–°Ω© ¨÷Ì»À", EntityType.PIG_ZOMBIE);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		PigZombie p = (PigZombie) e;
		p.setBaby(true);
		return e;
	}

}
