package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_ZombiePigman extends MobType {
	public MobType_ZombiePigman() {
		super("Ω© ¨÷Ì»À", EntityType.PIG_ZOMBIE);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		PigZombie p = (PigZombie) e;
		p.setBaby(false);
		return e;
	}

}
