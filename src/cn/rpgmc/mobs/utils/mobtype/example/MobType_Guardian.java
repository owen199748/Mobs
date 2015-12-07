package cn.rpgmc.mobs.utils.mobtype.example;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;

import cn.rpgmc.mobs.utils.mobtype.MobType;

public class MobType_Guardian extends MobType {
	public MobType_Guardian() {
		super(" ÿŒ¿’ﬂ", EntityType.GUARDIAN);
	}

	@Override
	public LivingEntity modify(LivingEntity e) {
		Guardian g = (Guardian) e;
		g.setElder(false);
		return e;
	}

}
