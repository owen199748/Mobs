package pw.owen.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;

import pw.owen.mobs.utils.mobtype.MobType;

public class MobType_IronGolem_PlayerCreated extends MobType {
	public MobType_IronGolem_PlayerCreated() {
		super("ÓÑÉÆÌú¿þÀÜ", EntityType.IRON_GOLEM);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		IronGolem i = (IronGolem) e;
		i.setPlayerCreated(true);
		return e;
	}

}
