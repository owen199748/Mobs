package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_IronGolem extends MobType {
	public MobType_IronGolem() {
		super("Ìú¿þÀÜ", EntityType.IRON_GOLEM);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		IronGolem i = (IronGolem) e;
		i.setPlayerCreated(false);
		return e;
	}

}
